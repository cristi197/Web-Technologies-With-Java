package springcontextexample.ex3.domain;

public class Cat {

    private int age;
    private String gender;
    public Cat(int age) {
        this.age = age;
    }

    public Cat(int age, String gender) {
        this.age = age;
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
