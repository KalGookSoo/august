package com.kalgookso.august.service.lms;

import com.kalgookso.august.entity.lms.Major;
import com.kalgookso.august.repository.lms.MajorRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class MajorServiceTest {

    private MajorService majorService;

    @Autowired
    private MajorRepository majorRepository;

    private Major testMajor;

    @BeforeEach
    public void setup() {
        majorService = new DefaultMajorService(majorRepository);
        Major major = new Major();
        major.setName("테스트 학과");
        testMajor = majorService.create(major);
    }

    @Test
    @DisplayName("학과를 생성합니다.")
    void createTest() {
        Major major = new Major();
        major.setName("테스트 학과");
        Major created = majorService.create(major);
        Assertions.assertThat(created.getId()).isNotNull();
    }

    @Test
    @DisplayName("학과 식별자로 학과를 조회합니다.")
    public void findMajorByIdTest() {
        Optional<Major> foundMajor = majorService.findById(testMajor.getId());
        Assertions.assertThat(foundMajor.isPresent()).isTrue();
        Assertions.assertThat(foundMajor.get().getName()).isEqualTo("테스트 학과");
    }

    @Test
    @DisplayName("학과명으로 학과 목록을 조회합니다.")
    public void findMajorTest() {
        List<Major> majors = majorService.findAllByName("테스트 학과");
        Assertions.assertThat(majors.isEmpty()).isFalse();
        List<String> names = majors.stream().map(Major::getName).collect(Collectors.toList());
        Assertions.assertThat(names).contains("테스트 학과");
    }

    @Test
    @DisplayName("학과를 제거합니다.")
    public void removeMajorTest() {
        majorService.remove(testMajor.getId());
        Optional<Major> foundMajor = majorService.findById(testMajor.getId());
        Assertions.assertThat(foundMajor.isPresent()).isFalse();
    }

    @Disabled
    @Test
    @DisplayName("학과 샘플 데이터를 생성합니다.")
    public void createMajorTest() {
        List<String> majorNames = Arrays.asList("국어국문학", "중어중국학", "일본학", "영어영문학", "문헌정보학", "평생교육·청소년상담학", "아동학", "광고홍보학", "미디어커뮤니케이션학", "법학", "경찰행정학", "소방방재행정학", "행정학", "사회복지학", "경제금융보험학", "재무부동산학", "부동산금융·자산경영학", "유통물류학", "스마트항만물류학", "창업투자경영학", "경영학", "회계학", "국제관광경영학", "호텔·컨벤션경영학", "외식경영학", "스마트호스피탈리티학", "계약학", "간호학", "임상병리학", "치위생학", "방사선학", "의료경영학", "물리치료학", "보육·가정상담학", "식품영양학", "한의학", "미래형자동차학", "계약학", "조선해양공학", "컴퓨터공학", "디지털콘텐츠학", "게임공학", "영화학", "소프트웨어융합학", "계약학", "인공지능학", "음악학", "디자인조형학", "패션디자인학", "체육학", "레저스포츠학", "태권도학", "경기지도학");

        for (String majorName : majorNames) {
            Major major = new Major();
            major.setName(majorName);
            majorService.create(major);
        }
    }

}