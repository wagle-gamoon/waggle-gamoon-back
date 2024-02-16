package com.gamoon.gamoonbe.domain.match.appication;

import com.gamoon.gamoonbe.domain.match.domain.Student;
import com.gamoon.gamoonbe.domain.match.domain.MatchRoom;
import com.gamoon.gamoonbe.domain.match.domain.WhoMeet;
import com.gamoon.gamoonbe.domain.match.domain.Alumni;
import com.gamoon.gamoonbe.domain.match.repository.RoomRepository;
import com.gamoon.gamoonbe.domain.match.repository.WhoMeetRepository;
import com.gamoon.gamoonbe.domain.users.domain.Users;
import com.gamoon.gamoonbe.domain.users.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.*;

public class GaleShapley {
    private final int N; // 총 커플 수
    private int engagedCount;  // 현재 매칭된 커플 수
    private Map<String, Student> students;  // 모든 남자를 저장하는 맵
    private Map<String, Alumni> graduates;  // 모든 여자를 저장하는 맵
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final WhoMeetRepository whoMeetRepository;

    // 생성자
    public GaleShapley(Map<String, Student> m, Map<String, Alumni> w, RoomRepository roomRepository, UserRepository userRepository, WhoMeetRepository whoMeetRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.whoMeetRepository = whoMeetRepository;
        N = Math.min(m.size(), w.size());
        engagedCount = 0;
        students = m;
        graduates = w;
        for (Student student : students.values()) {
            student.setPreferences(new ArrayList<>(student.getPreferences()));
        }
        for (Alumni alumni : graduates.values()) {
            alumni.setPreferences(new ArrayList<>(alumni.getPreferences()));
        }
        calcMatches();  // 매칭을 계산하는 메소드 호출
    }

    // 매칭을 계산하는 메소드
    private void calcMatches() {
        System.out.println("매칭을 계산합니다.");
        firstPropose();  // 모든 남자가 첫 번째 선호도의 여성에게 고백
        firstAcceptOrReject();  // 각 여성이 고백한 남자 중 가장 선호하는 남자를 선택
        while (engagedCount < N) {  // 모든 커플이 매칭될 때까지 반복
            Student free = null;
            for (Student student : students.values()) {  // 매칭되지 않은 남자를 찾음
                if (!student.isEngaged()) {
                    free = student;
                    break;
                }
            }

            if (free != null) {  // 매칭되지 않은 남자가 있으면
                System.out.println("남자 " + free.getName() + "이(가) 매칭을 시도합니다.");

                for (String w : free.getPreferences()) {  // 그 남자의 선호도 목록을 순회
                    Alumni alumni = graduates.get(w);  // 선호하는 여자를 찾음
                    if (!free.isEngaged() && !free.getProposed().contains(alumni)) {  // 남자가 아직 매칭되지 않았고, 아직 고백하지 않은 여성에게만 고백
                        System.out.println("   " + free.getName() + "이(가) " + alumni.getName() + "에게 고백합니다.");
                        free.getProposed().add(alumni);  // 남자가 고백한 여성을 기록

                        if (alumni.getPartner() == null) {  // 여자가 아직 매칭되지 않았으면
                            alumni.setPartnerUserId(free.getUser().getUserId()); // 여자와 남자를 매칭
                            alumni.setPartner(free.getName());
                            free.setEngaged(true);  // 남자의 상태를 매칭됨으로 변경
                            engagedCount++;  // 매칭된 커플 수 증가
                            System.out.println("   " + alumni.getName() + "은(는) " + free.getName() + "과(와) 매칭되었습니다.");

                        } else {  // 여자가 이미 매칭되어 있으면
                            Student currentPartner = students.get(alumni.getPartner());
//                            Man currentPartner = men.get(woman.getPartnerUserId());  // 현재 파트너를 찾음
                            System.out.println("   " + alumni.getName() + "은(는) 이미 " + currentPartner.getName() + "과(와) 매칭되어 있습니다.");

                            if (morePreference(currentPartner, free, alumni)) {  // 여자가 새로운 남자를 더 선호하면
                                alumni.setPartnerUserId(free.getUser().getUserId());// 여자와 새로운 남자를 매칭
                                alumni.setPartner(free.getName());  // 여자와 새로운 남자를 매칭
                                free.setEngaged(true);  // 새로운 남자의 상태를 매칭됨으로 변경
                                currentPartner.setEngaged(false);  // 현재 파트너의 상태를 매칭되지 않음으로 변경
                                System.out.println("   " + alumni.getName() + "은(는) " + free.getName() + "과(와) 매칭되었습니다. (이전 매칭 해제)");
                            } else {
                                System.out.println("   " + alumni.getName() + "은(는) 여전히 " + currentPartner.getName() + "과(와) 매칭되어 있습니다. (새로운 남자 선호도 부족)");
                            }
                        }
                    }
                }
            }
        }
        printCouples();  // 최종 매칭 결과 출력
    }

    // 모든 남자가 첫 번째 선호도의 여성에게 고백하는 메서드
    private void firstPropose() {
        System.out.println("모든 남자가 첫 번째 선호도의 여성에게 고백합니다.");
        for (Student student : students.values()) {
            Alumni alumni = graduates.get(student.getPreferences().get(0));
            alumni.getProposals().add(student);
            student.getProposed().add(alumni);  // 남자가 고백한 여성을 기록
            System.out.println(student.getName() + "이(가) " + alumni.getName() + "에게 고백했습니다.");
        }
    }

    // 각 여성이 고백한 남자 중 가장 선호하는 남자를 선택하는 메소드
    private void firstAcceptOrReject() {
        System.out.println("각 여성이 고백한 남자 중 가장 선호하는 남자를 선택합니다.");
        for (Alumni alumni : graduates.values()) {
            if (!alumni.getProposals().isEmpty()) {
                Student chosenStudent = alumni.getProposals().get(0);
                for (Student student : alumni.getProposals()) {
                    if (alumni.getPreferences().indexOf(student.getName()) < alumni.getPreferences().indexOf(chosenStudent.getName())) {
                        chosenStudent = student;
                    }
                }
                alumni.setPartnerUserId(chosenStudent.getUser().getUserId());
                alumni.setPartner(chosenStudent.getName());
                chosenStudent.setEngaged(true);
                engagedCount++;
                System.out.println(alumni.getName() + "이(가) " + chosenStudent.getName() + "을(를) 선택했습니다.");

                alumni.getProposals().remove(chosenStudent);
                for (Student student : alumni.getProposals()) {
                    student.setEngaged(false);
                    student.getPreferences().remove(alumni.getName());
                }
                alumni.getProposals().clear();
            }
        }
    }

    // 여자가 새로운 남자를 더 선호하는지 확인하는 메소드
    public boolean morePreference(Student curPartner, Student newPartner, Alumni w) {
        for (String m : w.getPreferences()) {  // 여자의 선호도 목록을 순회
            if (m.equals(newPartner.getName()))  // 새로운 남자가 먼저 나오면 true 반환
                return true;
            if (m.equals(curPartner.getName()))  // 현재 파트너가 먼저 나오면 false 반환
                return false;
        }
        return false;
    }

    public void printCouples() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("=============== 최종결과 ===============");
        System.out.println("========================================");
        for (Alumni w : graduates.values()) {
            System.out.println(w.getName() + " - " + w.getPartner());

            // 여성과 파트너의 사용자 ID가 null이 아닌지 확인
            if (w.getUserId() != null && w.getPartnerUserId() != null) {
                Optional<Users> womenUser = userRepository.findById(w.getUserId());
                Optional<Users> partnerUser = userRepository.findById(w.getPartnerUserId());

                // 두 사용자 모두 데이터베이스에서 찾아진 경우에만 처리
                if (womenUser.isPresent() && partnerUser.isPresent()) {
                    // 채팅룸 생성 및 저장
                    MatchRoom matchRoom = MatchRoom.builder()
                            .user1(womenUser.get())
                            .user2(partnerUser.get())
                            .roomActive(true)
                            .createdAt(LocalDateTime.now())
                            .build();
                    roomRepository.save(matchRoom);

                    // 만난 사람 정보 저장
                    WhoMeet whoMeet = WhoMeet.builder()
                            .metUser1(womenUser.get())
                            .metUser2(partnerUser.get())
                            .createdAt(LocalDateTime.now())
                            .build();
                    whoMeetRepository.save(whoMeet);
                }
            }
        }
    }
}