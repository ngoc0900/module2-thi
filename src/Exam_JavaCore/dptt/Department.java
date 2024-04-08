package Exam_JavaCore.dptt;

import Exam_JavaCore.eple.Employee;
import Exam_JavaCore.run.BasicManagement;

import java.util.ArrayList;
import java.util.Scanner;

public class Department {
    private int departmentId;   //(mã phòng ban , tự tăng)
    private String departmentName;   //(tên phòng ban, không được trùng lặp, không để trống).

    public Department() {
    }

    public Department(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public  void displayData(){
            System.out.printf("Mã phòng ban: %d:, Tên phòng ban: %s,  \n", departmentId, departmentName);

    }
    private static ArrayList<Department> departments = new ArrayList<>();
    public static final Scanner scanner = new Scanner(System.in);

    public void inputData(Scanner scanner, int i) {
        System.out.println("Nhập thông tin cho phòng ban thứ " + i + ":");
        System.out.print("Nhập tên phòng ban: ");
        departmentName = scanner.nextLine();
        if (departmentName.isEmpty()) {
            System.out.println("Tên phòng ban không được để trống. Vui lòng nhập lại.");
            return;
        }
        boolean isDuplicate = departments.stream()
                .anyMatch(department -> department.getDepartmentName().equalsIgnoreCase(departmentName));
        if (isDuplicate) {
            System.out.println("Tên phòng ban đã tồn tại. Vui lòng nhập lại.");
            return;
        }
        departmentId = departments.size() + 1;
        Department department = new Department(departmentId, departmentName);
        departments.add(department);
    }

    public void editData() {
        do {
            System.out.println("Mời nhập tên phòng ban");
            departmentName = scanner.nextLine();
            if (departmentName.isEmpty()) {
                System.out.println("Tên phòng ban không được để trống. Vui lòng nhập lại.");
            } else {
                return;
            }

        } while (true);
    }

}
