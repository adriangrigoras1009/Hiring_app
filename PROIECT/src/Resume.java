import java.util.ArrayList;

public class Resume {

    private Information personal_datas;
    private ArrayList<Education> education = new ArrayList<Education>();
    private ArrayList<Experience> experience = new ArrayList<Experience>();
    private Resume(ResumeBuilder builder) {
        personal_datas = builder.personal_datas;
        education = builder.education;
        experience = builder.experience;
    }
    public Information getPersonal_datas() {
        return personal_datas;
    }
    public ArrayList<Education> getEducation() {
        return education;
    }
    public ArrayList<Experience> getExperience() {
        return experience;
    }
    public static class ResumeBuilder {
        private final Information personal_datas;
        private ArrayList<Education> education;
        private ArrayList<Experience> experience;
        public ResumeBuilder(Information personal_datas, Education first) {
            this.personal_datas = personal_datas;
            this.education = new ArrayList<Education>();
            this.experience = new ArrayList<Experience>();
            this.education.add(first);
        }
        public ResumeBuilder studii(Education education) {
            this.education.add(education);
            return this;
        }
        public ResumeBuilder munca(Experience experience) {
            this.experience.add(experience);
            return this;
        }
        public Resume build() {
            return new Resume(this);
        }
    }
}