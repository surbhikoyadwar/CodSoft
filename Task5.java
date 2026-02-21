import java.util.*;
import java.io.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    int roll;
    String grade;

    Student(String name, int roll, String grade) {
        this.name = name;
        this.roll = roll;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Roll: " + roll + " | Name: " + name + " | Grade: " + grade;
    }
}

class SystemLogic {
    List<Student> list = new ArrayList<>();
    String fileName = "data.txt";

    SystemLogic() {
        load();
    }

    void add(Student s) {
        list.add(s);
        save();
    }

    void remove(int roll) {
        list.removeIf(s -> s.roll == roll);
        save();
    }

    Student find(int roll) {
        for (Student s : list) {
            if (s.roll == roll)
                return s;
        }
        return null;
    }

    void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(list);
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    void load() {
        File f = new File(fileName);
        if (!f.exists())
            return;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            list = (List<Student>) in.readObject();
        } catch (Exception e) {
        }
    }
}

public class Task5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SystemLogic sys = new SystemLogic();

        while (true) {
            System.out.println("\n1. Add | 2. Show All | 3. Search | 4. Edit | 5. Remove | 6. Exit");
            System.out.print("Select: ");
            String op = sc.nextLine();

            try {
                if (op.equals("1")) {
                    System.out.print("Name: ");
                    String n = sc.nextLine();
                    System.out.print("Roll: ");
                    int r = Integer.parseInt(sc.nextLine());
                    System.out.print("Grade: ");
                    String g = sc.nextLine();

                    if (!n.isEmpty() && !g.isEmpty()) {
                        sys.add(new Student(n, r, g));
                        System.out.println("Student added.");
                    }
                } else if (op.equals("2")) {
                    if (sys.list.isEmpty())
                        System.out.println("No students found.");
                    for (Student s : sys.list)
                        System.out.println(s);
                } else if (op.equals("3")) {
                    System.out.print("Roll to find: ");
                    int r = Integer.parseInt(sc.nextLine());
                    Student s = sys.find(r);
                    System.out.println(s != null ? s : "Not found.");
                } else if (op.equals("4")) {
                    System.out.print("Roll to edit: ");
                    int r = Integer.parseInt(sc.nextLine());
                    Student s = sys.find(r);
                    if (s != null) {
                        System.out.print("New Name (blank to skip): ");
                        String n = sc.nextLine();
                        System.out.print("New Grade (blank to skip): ");
                        String g = sc.nextLine();
                        if (!n.isEmpty())
                            s.name = n;
                        if (!g.isEmpty())
                            s.grade = g;
                        sys.save();
                        System.out.println("Modified.");
                    } else {
                        System.out.println("Not found.");
                    }
                } else if (op.equals("5")) {
                    System.out.print("Roll to delete: ");
                    int r = Integer.parseInt(sc.nextLine());
                    sys.remove(r);
                    System.out.println("Deleted.");
                } else if (op.equals("6")) {
                    System.out.println("Exiting...");
                    break;
                } else {
                    System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Input error. Please enter details correctly.");
            }
        }
        sc.close();
    }
}
