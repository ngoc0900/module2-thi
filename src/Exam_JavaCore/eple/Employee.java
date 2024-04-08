package Exam_JavaCore.eple;

import Exam_JavaCore.dptt.Department;
import Exam_JavaCore.run.BasicManagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Employee {
    private int employeeId;
    private String employeeName;
    private String email;
    private String phoneNumber ;
    private String address;
    private boolean gender;
    private Date birthday;
    private float basicSalary;
    private float allowanceSalary;
    private float rate;
    private int departmentId;

    public Employee() {
    }

    public Employee(int employeeId,String employeeName, String email, String phoneNumber, String address, boolean gender, Date birthday, float basicSalary, float allowanceSalary, float rate, int departmentId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.birthday = birthday;
        this.basicSalary = basicSalary;
        this.allowanceSalary = allowanceSalary;
        this.rate = rate;
        this.departmentId = departmentId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public float getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(float basicSalary) {
        this.basicSalary = basicSalary;
    }

    public float getAllowanceSalary() {
        return allowanceSalary;
    }

    public void setAllowanceSalary(float allowanceSalary) {
        this.allowanceSalary = allowanceSalary;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
    public float calTotalSalary() {
        return basicSalary * rate + allowanceSalary;
    }

    public  ArrayList<Employee> employees = new ArrayList<>();
    public  ArrayList<Department> departments = new ArrayList<>();
    public static final Scanner scanner = new Scanner(System.in);

    private static boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    public int getBirthDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.birthday);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getBirthMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.birthday);
        return calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0 nên cần cộng thêm 1
    }

    public int getBirthYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.birthday);
        return calendar.get(Calendar.YEAR);
    }

    public int getAge() {
        LocalDate today = LocalDate.now();
        int currenrYear = today.getYear();
        return currenrYear - getBirthYear() ;
    }

    public Employee inputData(Scanner scanner , int id){
        employeeId = id +1;
        System.out.println("Nhân viên thứ:" + employeeId );
//        System.out.println("Mời nhập mã nhân viên");
//        employeeId = Integer.parseInt(scanner.nextLine());

        System.out.println("Mời nhập tên nhân viên");
        employeeName = scanner.nextLine();
        if (employeeName.isEmpty()) {
            System.out.println("Tên nhân viên không được để trống. Vui lòng nhập lại.");
            employeeName = scanner.nextLine();
        }
        System.out.println("Mời nhập gmail");
        email = scanner.nextLine();
        System.out.println("Mời nhập số điện thoại");
        phoneNumber = scanner.nextLine();
        System.out.println("Mời nhập địa chỉ");
        address = scanner.nextLine();
        System.out.println("Mời nhập giới tính (điền true/false)");
        gender = Boolean.parseBoolean(scanner.nextLine());
        System.out.print("Ngày sinh (dd/MM/yyyy): ");
        String birthdayStr = scanner.nextLine();
        while (!isValidDate(birthdayStr)) {
            System.out.print("Ngày sinh không hợp lệ, vui lòng nhập lại (dd/MM/yyyy): ");
            birthdayStr = scanner.nextLine();
        }

        try {
            this.birthday = new SimpleDateFormat("dd/MM/yyyy").parse(birthdayStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Mời nhập lương cơ bản");
        basicSalary = Float.parseFloat(scanner.nextLine());
        System.out.println("Mời nhập phụ cấp");
        allowanceSalary = Float.parseFloat(scanner.nextLine());
        System.out.println("Mời nhập hệ số lương");
        rate = Float.parseFloat(scanner.nextLine());

        System.out.println("Chọn mã phòng ban làm việc");
        for (Department department : BasicManagement.departments) {
            System.out.println(department.getDepartmentId()+" "+department.getDepartmentName());
        }
        boolean check = false;
        do {
            try {
                System.out.println("Nhập vào mã phòng ban");
                int id1 = Integer.parseInt(scanner.nextLine());
                boolean check1 = false;
                for (Department department : BasicManagement.departments) {
                    if (department.getDepartmentId() == id1){
                        check1 = true;
                        departmentId = id1;
                    }
                }
                if (check1){
                    check = true;
                }else {
                    System.out.println("Không tồn tại ID này " + id1);
                }
            }catch (Exception e){
                System.err.println("Định dạng sai, mời nhập lại.");
            }
        }while (!check);
//        employeeId = employees.size() + 1;
        Employee employee = new Employee(employeeId,employeeName,email,phoneNumber,
                address,gender,birthday,basicSalary,allowanceSalary,rate,departmentId );

//        employees.add(employee);
        return employee;
    }
    public void displayData(){
        System.out.printf("Mã nhân viên: %d, Tên nhân viên: %s, " +
                "Email: %s , SĐT: %s, Địa chỉ: %s ,",employeeId, employeeName,email,phoneNumber,address);
        System.out.printf("Giới tính: %s, " ,(gender? "MALE" : "FEMALE"));
        System.out.print("Ngày sinh: "+ getBirthDay()+"/"+getBirthMonth()+"/"+getBirthYear());
        for (Department department : BasicManagement.departments) {
            if (department.getDepartmentId() == departmentId){
                System.out.print(", Tên phòng ban: "+ department.getDepartmentName());
            }
        }
        System.out.println(", Tổng lương: "+ calTotalSalary()+ "$");
    }
    public  void show(){
        System.out.printf("Mã nhân viên: %d:, Tên nhân viên: %s, ",employeeId, employeeName);
        System.out.printf("Giới tính: %s, " ,(gender? "MALE" : "FEMALE"));
        System.out.printf("Tuổi: %d, ",getAge());
        for (Department department : BasicManagement.departments) {
            if (department.getDepartmentId() == departmentId){
                System.out.println("Tên phòng ban: "+ department.getDepartmentName());
            }
        }
    }
    public void showChiTiet(){
        System.out.println("Chọn mã nhân viên cần xem");
        for (Employee employee : BasicManagement.employees) {
            System.out.println("Mã NV: "+employee.getEmployeeId()+ ", Tên NV: "+ employee.getEmployeeName());
        }
        boolean check = false;
        do {
            try {
                System.out.println("Nhập vào mã nhân viên");
                int choice = Integer.parseInt(scanner.nextLine());
                boolean check1 = false;
                for (Employee employee : BasicManagement.employees) {
                    if (employee.getEmployeeId() == choice){
                        check1 = true;
                        displayData();
                        break;
                    }

                }
                if (check1){
                    check = true;
                }else {
                    System.out.println("Không tồn tại ID này " + choice);
                }
            }catch (Exception e){
                System.err.println("Định dạng sai, mời nhập lại.");
            }
        }while (!check);
    }

    public void editData(){
        System.out.println("Mời nhập tên nhân viên");
        employeeName = scanner.nextLine();
        if (employeeName.isEmpty()) {
            System.out.println("Tên nhân viên không được để trống. Vui lòng nhập lại.");
            return;
        }
        System.out.println("Mời nhập gmail");
        email = scanner.nextLine();
        System.out.println("Mời nhập số điện thoại");
        phoneNumber = scanner.nextLine();
        System.out.println("Mời nhập địa chỉ");
        address = scanner.nextLine();
        System.out.println("Mời nhập giới tính (điền true/false)");
        gender = Boolean.parseBoolean(scanner.nextLine());
        System.out.print("Ngày sinh (dd/MM/yyyy): ");
        String birthdayStr = scanner.nextLine();
        while (!isValidDate(birthdayStr)) {
            System.out.print("Ngày sinh không hợp lệ, vui lòng nhập lại (dd/MM/yyyy): ");
            birthdayStr = scanner.nextLine();
        }

        try {
            this.birthday = new SimpleDateFormat("dd/MM/yyyy").parse(birthdayStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Mời nhập lương cơ bản");
        basicSalary = Float.parseFloat(scanner.nextLine());
        System.out.println("Mời nhập phụ cấp");
        allowanceSalary = Float.parseFloat(scanner.nextLine());
        System.out.println("Mời nhập hệ số lương");
        rate = Float.parseFloat(scanner.nextLine());

        System.out.println("Chọn mã phòng ban làm việc");
        for (Department department : BasicManagement.departments) {
            System.out.println(department.getDepartmentId()+" "+department.getDepartmentName());
        }
        boolean check = false;
        do {
            try {
                System.out.println("Nhập vào mã phòng ban");
                int id = Integer.parseInt(scanner.nextLine());
                boolean check1 = false;
                for (Department department : BasicManagement.departments) {
                    if (department.getDepartmentId() == id){
                        check1 = true;
                        departmentId = id;
                    }
                }
                if (check1){
                    check = true;
                }else {
                    System.out.println("Không tồn tại ID này " + id);
                }
            }catch (Exception e){
                System.err.println("Định dạng sai, mời nhập lại.");
            }
        }while (!check);
    }
}




//System.out.println("mời banh nhập vào tên nhân viên");
//String name = scanner.nextLine();
//employeeName = name;
//
//        System.out.println("mời bạn nhập vào địa chỉ email");
//email = scanner.nextLine();
//        System.out.println("mời bạn nhập vào số điện thoại");
//
//phoneNumber = scanner.nextLine();
//int genderCheck=0;
//boolean genderCheckflag=false;
//        while (!genderCheckflag){
//        System.out.println("mời bạn chọn giới tính");
//            System.out.println("1:nam");
//            System.out.println("2:nữ");
//            try {
//genderCheck=Integer.parseInt(scanner.nextLine());
//        if (genderCheck==1||genderCheck==2){
//        if (genderCheck == 1) {
//gender = true;
//        } else {
//gender = false;
//        }
//genderCheckflag=true;
//        }
//        }catch (Exception e){
//        System.out.println("lỗi");
//            }
//                    }
