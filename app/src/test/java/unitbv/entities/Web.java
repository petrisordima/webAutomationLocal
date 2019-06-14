package unitbv.entities;

public class Web {

    public static final String PLATFORM_URL = "https://elearning.unitbv.ro/login/index.php";


    public class LoginPage {

        public static final String INPUT_USERNAME = "//input[@name='username']";
        public static final String INPUT_PASSWORD = "//input[@name='password']";
        public static final String BTN_LOGIN = "//input[@type='submit']";

    }

    public class HomePage {

        public static final String HOME_LOGO = "//img[contains(@class,'small-logo')]";

        public class Profile {
            public static final String PROFILE_PHOTO = "//span[contains(@class,'userbutton')]";
            public static final String PROFILE_GRADES = "//span[@class='menu-action-text'][contains(.,'Note')]";
        }
    }

    public class MyGradesPage {

        public static final String IS_COURSE = "//a[contains(.,'Inginerie software ID 2018-2019')]";
        public static final String IS_GRADE = "//td[contains(.,'80,00')]";

        public class ISGrades{

            public static final String COURSE_TOTAL = "//span[contains(@class,'gradeitemheader')]";

        }
    }
}
