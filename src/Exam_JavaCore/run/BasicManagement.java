package Exam_JavaCore.run;

import Exam_JavaCore.dptt.Department;
import Exam_JavaCore.eple.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class BasicManagement {

    public static ArrayList<Department> departments = new ArrayList<>();
    public static ArrayList<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.println("********************MENU********************\n" +
                        "1.\tQuản lý phòng ban \n" +
                        "2.\tQuản lý nhân viên \n" +
                        "3.\tThoát chương trình \n");
                System.out.println("Mời bạn chọn: ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        departmentManagement();
                        break;
                    case 2:
                        employeeManagement();
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Mời chọn lại từ 1-3");
                }
            } catch (Exception e) {
                System.err.println("Định dạng không hợp lệ, mời nhập lại.");
            }

        } while (true);
    }

    public static void departmentManagement() {
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.println("**************DEPARTMENT-MENU***************\n" +
                        "1.\tThêm mới phòng ban \n" +
                        "2.\tHiển thị thông tin tất cả phòng ban \n" +
                        "3.\tSửa tên phòng ban \n" +
                        "4.\tXóa phòng ban \n" +
                        "5.\tTìm kiếm phòng ban \n" +
                        "6.\tQuay lại \n");
                System.out.println("Mời bạn chọn: ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Số phòng ban muốn nhập");
                        int n = Integer.parseInt(scanner.nextLine());
                        for (int i = 0; i < n; i++) {
                            Department department = new Department();
                            department.inputData(scanner, i + 1);
                            departments.add(department);
                        }
                        break;
                    case 2:
                        System.out.println("Hiển thị thông tin tất cả phòng ban");
                        for (Department department : departments) {
                            int sum = 0;
                            for (Employee employee : employees) {
                                if (department.getDepartmentId() == employee.getDepartmentId()) {
                                    sum++;
                                }
                            }
                            System.out.printf("Mã phòng ban: %d:, Tên phòng ban: %s," +
                                    " Số nhân viên: %s \n", department.getDepartmentId(), department.getDepartmentName(), sum);
                        }
                        break;
                    case 3:
                        System.out.println("Sửa tên phòng ban");
                        try {
                            for (Department department : departments) {
                                System.out.println("DepartmentId: " + department.getDepartmentId() +
                                        ", DepartmentName: " + department.getDepartmentName());
                            }
                            System.out.println("Mời nhập Id phòng ban cần sửa");
                            int id = Integer.parseInt(scanner.nextLine());
                            boolean check = true;
                            for (Department department1 : departments) {
                                if (department1.getDepartmentId() == id) {
                                    department1.displayData();
                                    check = false;
                                    System.out.println("Bạn có muốn sửa lại thông tin k? (c/k)");
                                    String choice1 = scanner.nextLine();
                                    if (choice1.equalsIgnoreCase("c")) {
                                        department1.editData();
                                        System.out.println("Đã edit xong");
                                        break;
                                    } else {
                                        System.out.println("Huỷ sửa thông tin");
                                    }
                                }
                            }
                            if (check) {
                                System.err.println("không tồn tại id phòng ban mà bạn cần sửa.");
                            }
                        } catch (Exception e) {
                            System.err.println("Định dạng không đúng, mời nhập lại.");
                        }
                        break;
                    case 4:
                        System.out.println("Xóa phòng ban");
                        try {
                            if (departments.isEmpty()) {
                                System.err.println("Không có phòng ban nào để xóa.");
                                return;
                            }
                            for (Department department : departments) {
                                System.out.println("DepartmentId: " + department.getDepartmentId() + 
                                        ", DepartmentName: " + department.getDepartmentName());
                            }
                            System.out.println("Mời nhập Id phòng ban cần xoá");
                            int id = Integer.parseInt(scanner.nextLine());
                            boolean hasEmployees = false;
                            for (Employee employee : employees) {
                                if (employee.getDepartmentId() == id) {
                                    hasEmployees = true;
                                    break;
                                }
                            }
                            if (hasEmployees) {
                                System.err.println("Không thể xóa phòng ban vì có nhân viên thuộc phòng ban này.");
                            } else {
                                boolean check = true;
                                for (Department department1 : departments) {
                                    if (department1.getDepartmentId() == id) {
                                        department1.displayData();
                                        check = false;
                                        System.out.println("bạn có muốn xoá thông tin k? (c/k)");
                                        String choice1 = scanner.nextLine();
                                        if (choice1.equalsIgnoreCase("c")) {
                                            departments.remove(department1);
                                            System.out.println("đã xoá xong");
                                            break;
                                        } else {
                                            System.out.println("Huỷ xóa thông tin");
                                        }
                                    }
                                }
                                if (check) {
                                    System.err.println("không tồn tại id phòng ban mà bạn mốn xoá.");
                                }
                            }

                        } catch (Exception e) {
                            System.err.println("Định dạng không đúng, mời nhập lại.");
                        }
                        break;
                    case 5:
                        System.out.println("Tìm kiếm phòng ban");
                        try {
                            System.out.println("Mời nhập tên phòng ban cần tìm");
                            String name = scanner.nextLine();
//                            boolean departments1 = departments.stream()
//                                    .anyMatch(department -> department.getDepartmentName().toLowerCase().contains(name.toLowerCase()));
//                            for (Department department : departments) {
//                                if (departments1) {
//                                    department.displayData();
//                                    break;
//                                } else {
//                                    System.err.println("không tồn tại tên phòng ban mà bạn mốn tìm.");
//                                }
//                            }
//
                            boolean check = true;
                            for (Department department1 : departments) {
                                if (department1.getDepartmentName().toLowerCase().contains(name.toLowerCase())) {
                                    department1.displayData();
                                    check = false;
                                }
                            }
                            if (check) {
                                System.err.println("không tồn tại tên phòng ban mà bạn mốn tìm.");
                            }
                        } catch (Exception e) {
                            System.err.println("Định dạng không đúng, mời nhập lại.");
                        }
                        break;
                    case 6:
                        run();
                        break;
                    default:
                        System.err.println("Mời chọn lại từ 1-3");
                }
            } catch (Exception e) {
                System.err.println("Định dạng không hợp lệ, mời nhập lại.");
            }
        } while (true);
    }

    public static void employeeManagement() {
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.println("*****************EMPLOYEE-MENU******************\n" +
                        "1.\tThêm mới nhân viên \n" +
                        "2.\tHiển thị thông tin tất cả nhân viên\n" +
                        "3.\tXem chi tiết thông tin nhân viên \n" +
                        "4.\tThay đổi thông tin nhân viên \n" +
                        "5.\tXóa nhân viên \n" +
                        "6.\tHiển thị danh sách nhân viên theo phòng ban \n" +
                        "7.\tSắp xếp danh sách nhân viên\n" +
                        "8.\tQuay lại \n");
                System.out.println("Mời bạn chọn: ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Số nhân viên muốn nhập");
                        int n = Integer.parseInt(scanner.nextLine());
                        for (int i = 0; i < n; i++) {
                            Employee employee = new Employee();
                            employee.inputData(scanner, employees.size());
                            employees.add(employee);
                        }
                        break;
                    case 2:
                        System.out.println("Hiển thị thông tin tất cả các nhân viên");
                        for (Employee employee : employees) {
                            employee.show();
                        }
                        break;
                    case 3:
                        System.out.println("Xem chi tiết thông tin nhân viên");
                        for (Employee employee : employees) {
                            employee.showChiTiet();
                        }
                        break;
                    case 4:
                        System.out.println("Thay đổi thông tin nhân viên");
                        try {
                            for (Employee employee : employees) {
                                employee.show();
                            }
                            System.out.println("Mời nhập Id nhân viên cần thay đổi");
                            int id = Integer.parseInt(scanner.nextLine());
                            boolean check = true;
                            for (Employee employee : employees) {
                                if (employee.getEmployeeId() == id){
                                    employee.displayData();
                                    check = false;
                                    System.out.println("Bạn có muốn sửa lại thông tin k? (c/k)");
                                    String choice1 = scanner.nextLine();
                                    if (choice1.equalsIgnoreCase("c")){
                                        employee.editData();
                                        System.out.println("Đã edit xong");
                                        break;
                                    } else {
                                        System.out.println("Huỷ sửa thông tin");
                                    }
                                }
                            }
                            if (check) {
                                System.err.println("không tồn tại id nhân viên mà bạn cần thay đổi.");
                            }
                        }catch (Exception e){
                            System.err.println("Định dạng không hợp lệ, mời nhập lại.");
                        }
                        break;
                    case 5:
                        System.out.println("Xóa nhân viên");
                        try {
                            if (departments.isEmpty()) {
                                System.err.println("Không có nhân viên nào để xóa.");
                                return;
                            }
                            for (Employee employee : employees) {
                                employee.show();
                            }
                            System.out.println("Mời nhập Id nhân viên cần xoá");
                            int id = Integer.parseInt(scanner.nextLine());
                            boolean check = true;
                            for (Employee employee : employees) {
                                if (employee.getEmployeeId() == id) {
                                    employee.displayData();
                                    check = false;
                                    System.out.println("bạn có muốn xoá thông tin k? (c/k)");
                                    String choice1 = scanner.nextLine();
                                    if (choice1.equalsIgnoreCase("c")) {
                                        employees.remove(employee);
                                        System.out.println("đã xoá xong");
                                        break;
                                    } else {
                                        System.out.println("Huỷ xóa thông tin");
                                    }
                                }
                            }
                            if (check) {
                                System.err.println("không tồn tại id nhân viên mà bạn mốn xoá.");
                            }
                        }catch (Exception e){
                            System.err.println("Định dạng không hợp lệ, mời nhập lại.");
                        }
                        break;
                    case 6:
                        System.out.println("Hiển thị danh sách nhân viên theo phòng ban");
//                        displayEmployeesByDepartment();
                        for (Department department : departments) {
                            System.out.println(department.getDepartmentName());
                            Employee employee = getEmployeeByIdDepartment(employees,department.getDepartmentId());
                            if (employee != null){
                                getEmployeeByIdDepartment(employees,department.getDepartmentId());
                                break;
                            }
                        }
                        break;
                    case 7:
                        System.out.println("Sắp xếp danh sách nhân viên");
                        if (employees.isEmpty()) {
                            System.out.println("Không có nhân viên nào để sắp xếp.");
                            return;
                        }

                        System.out.println("Chọn thuộc tính muốn sắp xếp:");
                        System.out.println("1. Tên nhân viên");
                        System.out.println("2. Tuổi");
                        System.out.println("3. Phòng ban");
                        int choice2 = Integer.parseInt(scanner.nextLine());

                        Comparator<Employee> comparator = null;
                        switch (choice2) {
                            case 1:
                                comparator = Comparator.comparing(Employee::getEmployeeName);
                                break;
                            case 2:
                                comparator = Comparator.comparing(Employee::getAge);
                                break;
                            case 3:
                                comparator = Comparator.comparing(employee -> {
                                    for (Department department : departments) {
                                        if (department.getDepartmentId() == employee.getDepartmentId()) {
                                            return department.getDepartmentName();
                                        }
                                    }
                                    return null;
                                });
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ.");
                                return;
                        }

                        System.out.println("Chọn chiều sắp xếp:");
                        System.out.println("1. Tăng dần");
                        System.out.println("2. Giảm dần");
                        int order = Integer.parseInt(scanner.nextLine());
                        if (order == 2) {
                            comparator = comparator.reversed();
                        }
                        employees.sort(comparator);
                        System.out.println("Danh sách nhân viên sau khi sắp xếp:");
                        for (Employee employee : employees) {
                            employee.show();
                        }
                        break;
                    case 8:
                        run();
                    default:
                        System.err.println("Mời chọn lại từ 1-3");
                }
            } catch (Exception e) {
                System.err.println("Định dạng không hợp lệ, mời nhập lại.");
            }
        } while (true);
    }
    private static void displayEmployeesByDepartment() {
        Scanner scanner = new Scanner(System.in);
        if (departments.isEmpty()) {
            System.out.println("Không có phòng ban nào được tạo.");
            return;
        }

        System.out.println("Danh sách các phòng ban:");
        for (Department department : departments) {
            department.displayData();
        }

        System.out.print("Nhập mã phòng ban: ");
        int departmentId = Integer.parseInt(scanner.nextLine());
        boolean found = false;
        for (Department department : departments) {
            if (department.getDepartmentId() == departmentId) {
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy phòng ban có mã " + departmentId);
            return;
        }

        List<Employee> employeesInDepartment = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getDepartmentId() == departmentId) {
                employeesInDepartment.add(employee);
            }
        }

        if (employeesInDepartment.isEmpty()) {
            System.out.println("Không có nhân viên thuộc phòng ban này.");
            return;
        }

        System.out.println("Danh sách nhân viên thuộc phòng ban: ");
        for (Employee employee : employeesInDepartment) {
            employee.show();
        }
    }

    public static Employee getEmployeeByIdDepartment(List<Employee> employees , int id){
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == id){
                employee.show();
            }
        }
        return null;
    }

}
