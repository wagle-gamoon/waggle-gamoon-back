package com.gamoon.gamoonbe.domain.match.appication;

import com.gamoon.gamoonbe.domain.match.domain.Student;
import com.gamoon.gamoonbe.domain.match.domain.Person;
import com.gamoon.gamoonbe.domain.match.domain.WhoMeet;
import com.gamoon.gamoonbe.domain.match.domain.Alumni;
import com.gamoon.gamoonbe.domain.match.repository.RoomRepository;
import com.gamoon.gamoonbe.domain.match.repository.WhoMeetRepository;
import com.gamoon.gamoonbe.domain.users.domain.Prefer;
import com.gamoon.gamoonbe.domain.users.repository.PreferRepository;
import com.gamoon.gamoonbe.domain.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchingService {

    private final PreferRepository preferRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository; // UserRepository 추가
    private final WhoMeetRepository whoMeetRepository;

    private List<Student> students = new ArrayList<>();
    private List<Alumni> graduate = new ArrayList<>();

    @Autowired
    public MatchingService(PreferRepository personRepository, RoomRepository roomRepository, UserRepository userRepository, WhoMeetRepository whoMeetRepository) {
        this.preferRepository = personRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.whoMeetRepository = whoMeetRepository;
    }

    public void match() {
        // 매칭 활성화 된 prefer 객체를 가져옴
        List<Prefer> activeMatchTrueAndGraduateStatus = preferRepository.findByUserMatchActiveAndGraduateStatus(true); // 졸업생
        activeMatchTrueAndGraduateStatus.addAll(preferRepository.findByUserMatchActiveAndGraduateStatus(false)); // 재학생

        System.out.println(activeMatchTrueAndGraduateStatus);


        // shuffle을 통해 무작위로 섞음
        Collections.shuffle(activeMatchTrueAndGraduateStatus);

        // Prefer 객체들을 Person 인스턴스로 변환하고 남성과 여성 리스트에 추가
        for (Prefer prefer : activeMatchTrueAndGraduateStatus) {
            Person person = new Person(prefer.getUser(), prefer);

            if (person.getGraduate() == true) {
                students.add(new Student(person.getUser(), person.getPrefer()));
            } else if (person.getGraduate() == false) {
                graduate.add(new Alumni(person.getUser(), person.getPrefer()));
            }
        }

        System.out.println(graduate);

    }

    public void grouping() {
        Map<String, Student> m = convertListToMap(students);
        Map<String, Alumni> w = convertListToMap(graduate);

        Map<String, Map<String, Student>> studentGroups = groupByInterests(m);
        Map<String, Map<String, Alumni>> alumniGroups = groupByInterests(w);

        // 각 그룹에 대해 Gale-Shapley 알고리즘 실행
        for (String interests : studentGroups.keySet()) {
            Map<String, Student> studentGroup = studentGroups.get(interests);
            Map<String, Alumni> alumniGroup = alumniGroups.get(interests);

            System.out.println();
            System.out.println("====================================");
            System.out.println(interests + " 그룹 매칭을 시작합니다.");
            System.out.println("====================================");

            // 각 그룹의 남자 목록 출력
            System.out.println(interests + " 그룹 남자:");
            for (Student student : studentGroup.values()) {
                System.out.println(student.getName());
            }

            // 각 그룹의 여자 목록 출력
            System.out.println(interests + " 그룹 여자:");
            for (Alumni alumni : alumniGroup.values()) {
                System.out.println(alumni.getName());
            }

            // 각 남자의 선호도 목록 생성
            for (Student student : studentGroup.values()) {
                List<String> mainPreferences = new ArrayList<>();
                Set<String> metFemalesSet = new HashSet<>();

                // 특정 남성과 매칭된 이력 조회
                List<WhoMeet> metFemales = whoMeetRepository.findByMetUser2(student.getUser());
                for (WhoMeet met : metFemales) {
                    metFemalesSet.add(met.getMetUser1().getUserNickname()); // 이전에 매칭된 여성 추가
                }

                // 여자 그룹을 순회하면서 남자의 선호도를 결정
                for (Alumni alumni : alumniGroup.values()) {
                    String womanName = alumni.getName();
                    if (metFemalesSet.contains(womanName)) {
                        continue; // 이전에 매칭된 여성은 제외
                    }

                    mainPreferences.add(womanName);
                }

                // 보조 선호도를 전체 선호도 목록에 추가
                mainPreferences.addAll(metFemalesSet);

                // 남자의 preferences 속성에 최종 선호도 목록 설정
                student.setPreferences(mainPreferences);

                System.out.println(student.getName() + "의 선호도 목록: " + mainPreferences);
            }

            // 각 여자의 선호도 목록 생성
            for (Alumni alumni : alumniGroup.values()) {
                List<String> mainPreferences = new ArrayList<>();
                Set<String> metMalesSet = new HashSet<>();

                // 특정 여성과 매칭된 이력 조회
                List<WhoMeet> metMales = whoMeetRepository.findByMetUser1(alumni.getUser());
                for (WhoMeet met : metMales) {
                    metMalesSet.add(met.getMetUser2().getUserNickname()); // 이전에 매칭된 남성 추가
                }

                // 남자 그룹을 순회하면서 여자의 선호도를 결정
                for (Student student : studentGroup.values()) {
                    String manName = student.getName();
                    if (metMalesSet.contains(manName)) {
                        continue; // 이전에 매칭된 남성은 제외
                    }

                    mainPreferences.add(student.getName());
                }

                // 보조 선호도를 전체 선호도 목록에 추가
                mainPreferences.addAll(metMalesSet);

                // 여자의 preferences 속성에 최종 선호도 목록 설정
                alumni.setPreferences(mainPreferences);

                System.out.println(alumni.getName() + "의 선호도 목록: " + mainPreferences);
            }

            // Gale-Shapley 알고리즘 실행
            new GaleShapley(studentGroup, alumniGroup, roomRepository, userRepository, whoMeetRepository);
        }
    }

    private <T extends Person> Map<String, T> convertListToMap(List<T> list) {
        Map<String, T> map = new LinkedHashMap<>();
        for (T person : list) {
            map.put(String.valueOf(person.getName()), person);
        }
        return map;
    }

    private static <T extends Person> Map<String, Map<String, T>> groupByInterests(Map<String, T> persons) {
        Map<String, Map<String, T>> groups = new HashMap<>();
        for (T person : persons.values()) {
            if (!groups.containsKey(person.getInterests())) {
                groups.put(person.getInterests(), new HashMap<>());
            }
            groups.get(person.getInterests()).put(person.getName(), person);
        }
        return groups;
    }
}
