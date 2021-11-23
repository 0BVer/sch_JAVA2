package midterm;

import java.util.ArrayList;

public class Student {
    public static void main(String[] args) {
        Student_Data Kim = new Student_Data("김영준", "2117E7459", "010-9364-5472", "dr0joon@gmail.com", 3);
        System.out.println(Kim.Student_name);

        Kim.add_Subject("Java2", "01"); //과목을 추가하는 코드입니다.
        Kim.show_sub_info(); //과목 정보를 raw하게 확인하는 개발용 코드입니다.
        Kim.change_Subject_midterm_score("Java2", "01", 20); //중간 평가 성적을 등록하는 코드입니다.
        Kim.change_Subject_attendance_present("Java2", "01", new int[]{1, 2, 3}); //1, 2, 3주차를 출석으로 등록 하는 코드입니다.
        Kim.show_Subject_attendance("Java2", "01"); //학생의 출결 정보를 출력합니다.
        Kim.show_Subject_attendance("Java2", "01", 1); //학생의 1주차 출결 정보를 출력합니다.
        Kim.show_Subject_score("Java2", "01"); //학생의 점수를 출력합니다

    }
}

class Student_Data {
    String Student_name; //학생 이름
    String Student_number; //학번
    String Student_phone_number; //연락처
    String Student_email; //이메일
    int Student_grade; //학년
    ArrayList Student_subject = new ArrayList(); //index 0:과목명, 1:분반, 2~16:1~15 주차별 출석 여부, 17:중간평가 점수, 18:기말평가 점수, 19:총 점수, 20:등급, ... 반복

    //학생 객체를 생성하려면 최소한 이름은 등록하여야 합니다.
    Student_Data(String Student_name) {
        this.Student_name = Student_name;
    }

    //모든 정보를 넣으면서 생성하는 생성자입니다.
    Student_Data(String Student_name, String Student_number, String Student_phone_number, String Student_email, int Student_grade) {
        this.Student_name = Student_name;
        this.Student_number = Student_number;
        this.Student_phone_number = Student_phone_number;
        this.Student_email = Student_email;
        this.Student_grade = Student_grade;
    }

    //학생의 중간 평가 점수를 리턴합니다. 에러 발생시 -1000을 리턴합니다. 성적 계산을 위해 사용할 수 있습니다.
    int return_Subject_midterm_score(String Subject_name, String Subject_class) {
        int Subject_array_size = this.Student_subject.size();
        if (check_Subject_null(Subject_array_size)) return -1000;
        int Subject_index = find_Subject_index(Subject_name, Subject_class, Subject_array_size);
        if (Subject_index == -1) return -1000;
        else return (int) this.Student_subject.get(Subject_index * 21 + 17);
    }

    //학생의 기말 평가 점수를 리턴합니다. 에러 발생시 -1000을 리턴합니다. 성적 계산을 위해 사용할 수 있습니다.
    int return_Subject_final_score(String Subject_name, String Subject_class) {
        int Subject_array_size = this.Student_subject.size();
        if (check_Subject_null(Subject_array_size)) return -1000;
        int Subject_index = find_Subject_index(Subject_name, Subject_class, Subject_array_size);
        if (Subject_index == -1) return -1000;
        else return (int) this.Student_subject.get(Subject_index * 21 + 18);
    }

    //학생의 총 점수를 리턴합니다. 에러 발생시 -1000을 리턴합니다. 성적 계산을 위해 사용할 수 있습니다.
    int return_Subject_total_score(String Subject_name, String Subject_class) {
        int Subject_array_size = this.Student_subject.size();
        if (check_Subject_null(Subject_array_size)) return -1000;
        int Subject_index = find_Subject_index(Subject_name, Subject_class, Subject_array_size);
        if (Subject_index == -1) return -1000;
        else return (int) this.Student_subject.get(Subject_index * 21 + 19);
    }

    //학생의 등급을 리턴합니다. 에러 발생시 문자열로 Null을 리턴합니다. 성적 계산을 위해 사용할 수 있습니다.
    String return_Subject_rank_score(String Subject_name, String Subject_class) {
        int Subject_array_size = this.Student_subject.size();
        if (check_Subject_null(Subject_array_size)) return "Null";
        int Subject_index = find_Subject_index(Subject_name, Subject_class, Subject_array_size);
        if (Subject_index == -1) return "Null";
        else return (String) this.Student_subject.get(Subject_index * 21 + 20);
    }

    //학생의 해당 주차 출결 정보를 리턴합니다. 에러 발생시 문자열로 Null을 리턴합니다. 성적 계산을 위해 사용할 수 있습니다.
    String return_Subject_attendance(String Subject_name, String Subject_class, int weeks) {
        int Subject_array_size = this.Student_subject.size();
        if (weeks < 1 || weeks > 15) return "ERROR";
        if (check_Subject_null(Subject_array_size)) return "ERROR";
        int Subject_index = find_Subject_index(Subject_name, Subject_class, Subject_array_size);
        if (Subject_index == -1) return "ERROR";
        else return (String) this.Student_subject.get(Subject_index * 21 + weeks + 1);
    }

    //학생에게 과목 정보를 추가합니다. 과목이름, 분반, 1~15주차 출결 정보, 중간 평가, 기말 평가, 총 점수, 등급이 한번에 생성됩니다.
    void add_Subject(String Subject_name, String Subject_class) {
        int Subject_array_size = this.Student_subject.size();
        int Subject_index = find_Subject_index(Subject_name, Subject_class, Subject_array_size);
        if (Subject_index == -1) {
            this.Student_subject.add(Subject_name);
            this.Student_subject.add(Subject_class);
            for (int i = 2; i < 17; i++) {
                this.Student_subject.add("출결 미입력");
            }
            this.Student_subject.add(0);
            this.Student_subject.add(0);
            this.Student_subject.add(0);
            this.Student_subject.add("등급 미입력");
            System.out.println(this.Student_name + " 학생 " + Subject_name + "과목, " + Subject_class + "반 정보를 생성했습니다.");
        } else {
            System.out.println("동일한 과목이 이미 존재합니다.");
        }


    }

    //학생의 과목 정보를 삭제합니다. 해당 과목이 있는지 확인하고, 과목이름, 분반, 1~15주차 출결 정보, 중간 평가, 기말 평가, 총 점수, 등급이 한번에 삭제됩니다.
    void del_Subject(String Subject_name, String Subject_class) {
        int Subject_array_size = this.Student_subject.size();
        if (check_Subject_null(Subject_array_size))
            return;

        int Subject_index = find_Subject_index(Subject_name, Subject_class, Subject_array_size);
        if (Subject_index == -1)
            return;
        else
            for (int i = 0; i < 21; i++)
                this.Student_subject.remove(i * 21);
        System.out.println(this.Student_name + " 학생 " + Subject_name + "과목, " + Subject_class + "반 정보를 삭제했습니다.");
        return;
    }

    //학생의 출석 체크를 합니다. 배열을 통해 한번에 여러 주차의 출석을 처리할 수 있습니다.
    void change_Subject_attendance_present(String Subject_name, String Subject_class, int[] weeks) {
        int Subject_array_size = this.Student_subject.size();
        if (check_weeks_not_correct(weeks))
            return;

        if (check_Subject_null(Subject_array_size))
            return;

        int Subject_index = find_Subject_index(Subject_name, Subject_class, Subject_array_size);
        if (Subject_index == -1)
            return;
        else
            for (int i = 0; i < weeks.length; i++)
                this.Student_subject.set(Subject_index * 21 + weeks[i] + 1, "출석");

        System.out.print(this.Student_name + " 학생 " + Subject_name + "과목, " + Subject_class + "반 ");
        for (int k = 0; k < weeks.length; k++)
            System.out.print(weeks[k] + ", ");
        System.out.println("주 출석 처리 했습니다.");
        return;
    }

    //학생의 결석 체크를 합니다. 배열을 통해 한번에 여러 주차의 결석을 처리할 수 있습니다.
    void change_Subject_attendance_absent(String Subject_name, String Subject_class, int[] weeks) {
        int Subject_array_size = this.Student_subject.size();
        if (check_weeks_not_correct(weeks))
            return;

        if (check_Subject_null(Subject_array_size))
            return;

        int Subject_index = find_Subject_index(Subject_name, Subject_class, Subject_array_size);
        if (Subject_index == -1)
            return;
        else
            for (int i = 0; i < weeks.length; i++)
                this.Student_subject.set(Subject_index * 21 + weeks[i] + 1, "결석");

        System.out.print(this.Student_name + " 학생 " + Subject_name + "과목, " + Subject_class + "반 ");
        for (int i = 0; i < weeks.length; i++)
            System.out.print(weeks[i] + ", ");
        System.out.println("주 결석 처리 했습니다.");
        return;
    }

    //학생의 지각 체크를 합니다. 배열을 통해 한번에 여러 주차의 지각을 처리할 수 있습니다.
    void change_Subject_attendance_tardy(String Subject_name, String Subject_class, int[] weeks) {
        int Subject_array_size = this.Student_subject.size();
        if (check_weeks_not_correct(weeks))
            return;

        if (check_Subject_null(Subject_array_size))
            return;

        int Subject_index = find_Subject_index(Subject_name, Subject_class, Subject_array_size);
        if (Subject_index == -1)
            return;
        else
            for (int i = 0; i < weeks.length; i++)
                this.Student_subject.set(Subject_index * 21 + weeks[i] + 1, "결석");

        System.out.print(this.Student_name + " 학생 " + Subject_name + "과목, " + Subject_class + "반 ");
        for (int k = 0; k < weeks.length; k++)
            System.out.print(weeks[k] + ", ");
        System.out.println("주 지각 처리 했습니다.");
        return;

    }

    //학생의 중간 평가 점수를 변경합니다.
    void change_Subject_midterm_score(String Subject_name, String Subject_class, int score) {
        int Subject_array_size = this.Student_subject.size();

        if (check_Subject_null(Subject_array_size))
            return;

        int Subject_index = find_Subject_index(Subject_name, Subject_class, Subject_array_size);
        if (Subject_index == -1)
            return;
        else
            this.Student_subject.set(Subject_index * 21 + 17, score);
        System.out.println(this.Student_name + " 학생 " + Subject_name + "과목, " + Subject_class + "반 중간 평가 성적을 " + score + "점으로 등록 했습니다.");
        return;
    }

    //학생의 기말 평가 점수를 변경합니다.
    void change_Subject_final_score(String Subject_name, String Subject_class, int score) {
        int Subject_array_size = this.Student_subject.size();

        if (check_Subject_null(Subject_array_size))
            return;

        int Subject_index = find_Subject_index(Subject_name, Subject_class, Subject_array_size);
        if (Subject_index == -1)
            return;
        else
            this.Student_subject.set(Subject_index * 21 + 18, score);
        System.out.println(this.Student_name + " 학생 " + Subject_name + "과목, " + Subject_class + "반 기말 평가 성적을 " + score + "점으로 등록 했습니다.");
        return;
    }

    //학생의 총 점수를 변경합니다.
    void change_Subject_total_score(String Subject_name, String Subject_class, int score) {
        int Subject_array_size = this.Student_subject.size();

        if (check_Subject_null(Subject_array_size))
            return;

        int Subject_index = find_Subject_index(Subject_name, Subject_class, Subject_array_size);
        if (Subject_index == -1)
            return;
        else
            this.Student_subject.set(Subject_index * 21 + 19, score);
        System.out.println(this.Student_name + " 학생 " + Subject_name + "과목, " + Subject_class + "반 최종 성적을 " + score + "점으로 등록 했습니다.");
        return;
    }

    //학생의 등급을 변경합니다.
    void change_Subject_rank_score(String Subject_name, String Subject_class, String rank) {
        int Subject_array_size = this.Student_subject.size();

        if (check_Subject_null(Subject_array_size))
            return;

        int Subject_index = find_Subject_index(Subject_name, Subject_class, Subject_array_size);
        if (Subject_index == -1)
            return;
        else
            this.Student_subject.set(Subject_index * 21 + 20, rank);
        System.out.println(this.Student_name + " 학생 " + Subject_name + "과목, " + Subject_class + "반 등급을 " + rank + "로 등록 했습니다.");
        return;
    }

    //학생의 과목 인덱스를 검색하는 메소드입니다. 클래스 내부에서 과목을 조회할 때 사용됩니다.
    int find_Subject_index(String Subject_name, String Subject_class, int Subject_array_size) {
        for (int i = 0; i < Subject_array_size / 21; i++) {
            if (this.Student_subject.get(i * 21 + 0) == Subject_name) {
                if (this.Student_subject.get(i * 21 + 1) == Subject_class) {
                    return i;
                }
            }
        }
        System.out.println(this.Student_name + " 학생의 정보에서 " + Subject_name + "과목, " + Subject_class + "반을 찾을 수 없습니다.");
        return -1;
    }

    //학생에게 과목이 존재하는지 검사합니다. 존재하지 않으면 true를 리턴합니다.
    boolean check_Subject_null(int Subject_array_size) {
        if (Subject_array_size < 10) {
            System.out.println(this.Student_name + " 학생의 정보에 등록된 과목이 없습니다.");
            return true;
        } else
            return false;
    }

    //출결 체크에서 처리할 주차 정보가 올바른지 검사합니다.
    boolean check_weeks_not_correct(int[] weeks) {
        for (int i = 0; i < weeks.length; i++)
            if (weeks[i] < 1 || weeks[i] > 15) {
                System.out.println("1 ~ 15 사이의 숫자(int) 값을 넣어주세요");
                return true;
            }
        return false;
    }

    //학생의 과목 정보를 모두 출력합니다.
    void show_sub_info() {
        System.out.println(this.Student_subject);
//        System.out.println(this.Student_subject.size());
    }

    //학생의 출결 정보를 출력합니다.
    void show_Subject_attendance(String Subject_name, String Subject_class) {
        int Subject_array_size = this.Student_subject.size();

        if (check_Subject_null(Subject_array_size))
            return;

        int Subject_index = find_Subject_index(Subject_name, Subject_class, Subject_array_size);
        if (Subject_index == -1)
            return;
        else {
            System.out.println(this.Student_name + " 학생 " + Subject_name + "과목, " + Subject_class + "반 출결 정보");
            int present = 0, absent = 0, tardy = 0, unknown = 0;
            for (int i = 1; i < 16; i++) {
                String temp = (String) this.Student_subject.get(Subject_index * 21 + i + 1);
                switch (temp) {
                    case "출석":
                        present++;
                        break;
                    case "결석":
                        absent++;
                        break;
                    case "지각":
                        tardy++;
                        break;
                    case "출결 미입력":
                        unknown++;
                        break;
                }
                System.out.println(i + "주차 " + temp);
            }
            System.out.println("출석 : " + present + " | 결석 : " + absent + " | 지각 : " + tardy + " | 미입력 : " + unknown);
            return;
        }
    }

    //학생의 해당 주차의 출결 정보를 출력합니다.
    void show_Subject_attendance(String Subject_name, String Subject_class, int weeks) {
        int Subject_array_size = this.Student_subject.size();

        if (weeks < 1 || weeks > 15) {
            System.out.println("1 ~ 15 사이의 숫자(int) 값을 넣어주세요");
            return;
        }

        if (check_Subject_null(Subject_array_size))
            return;

        int Subject_index = find_Subject_index(Subject_name, Subject_class, Subject_array_size);
        if (Subject_index == -1)
            return;
        else {
            System.out.println(this.Student_name + " 학생 " + Subject_name + "과목, " + Subject_class + "반 " + weeks + "주차 " + this.Student_subject.get(Subject_index * 21 + weeks + 1) + "입니다.");
            return;
        }
    }

    //학생의 과목 점수를 출력합니다.
    void show_Subject_score(String Subject_name, String Subject_class) {
        int Subject_array_size = this.Student_subject.size();

        if (check_Subject_null(Subject_array_size))
            return;

        int Subject_index = find_Subject_index(Subject_name, Subject_class, Subject_array_size);
        if (Subject_index == -1)
            return;
        else {
            System.out.println(this.Student_name + " 학생 " + Subject_name + "과목, " + Subject_class + "반 성적 정보");
            show_Subject_midterm_score(Subject_name, Subject_class);
            show_Subject_final_score(Subject_name, Subject_class);
            show_Subject_total_score(Subject_name, Subject_class);
            show_Subject_rank_score(Subject_name, Subject_class);
        }
    }

    //학생의 중간 평가 점수를 출력합니다.
    void show_Subject_midterm_score(String Subject_name, String Subject_class) {
        int Subject_array_size = this.Student_subject.size();

        if (check_Subject_null(Subject_array_size))
            return;

        int Subject_index = find_Subject_index(Subject_name, Subject_class, Subject_array_size);
        if (Subject_index == -1)
            return;
        else {
            int temp = (int) this.Student_subject.get(Subject_index * 21 + 17);
            if (temp == -1)
                System.out.println("중간 평가 : 미입력");
            else {
                System.out.println("중간 평가 : " + temp);
            }
            return;
        }
    }

    //학생의 기말 평가 점수를 출력합니다.
    void show_Subject_final_score(String Subject_name, String Subject_class) {
        int Subject_array_size = this.Student_subject.size();

        if (check_Subject_null(Subject_array_size))
            return;

        int Subject_index = find_Subject_index(Subject_name, Subject_class, Subject_array_size);
        if (Subject_index == -1) {
        } else {
            int temp = (int) this.Student_subject.get(Subject_index * 21 + 18);
            if (temp == -1)
                System.out.println("기말 평가 : 미입력");
            else
                System.out.println("기말 평가 : " + temp);
        }
    }

    //학생의 총 점수를 출력 합니다.
    void show_Subject_total_score(String Subject_name, String Subject_class) {
        int Subject_array_size = this.Student_subject.size();

        if (check_Subject_null(Subject_array_size))
            return;

        int Subject_index = find_Subject_index(Subject_name, Subject_class, Subject_array_size);
        if (Subject_index == -1) {
        } else {
            int temp = (int) this.Student_subject.get(Subject_index * 21 + 19);
            if (temp == -1)
                System.out.println("최종 성적 : 미입력");
            else
                System.out.println("최종 성적 : " + temp);
        }
    }

    //학생의 등급을 출력합니다.
    void show_Subject_rank_score(String Subject_name, String Subject_class) {
        int Subject_array_size = this.Student_subject.size();

        if (check_Subject_null(Subject_array_size))
            return;

        int Subject_index = find_Subject_index(Subject_name, Subject_class, Subject_array_size);
        if (Subject_index == -1) {
        } else {
            if (this.Student_subject.get(Subject_index * 21 + 20) == "등급 미입력")
                System.out.println("등급 : 미입력");
            else
                System.out.println("등급 : " + this.Student_subject.get(Subject_index * 21 + 20));
        }
    }
}
