package org.back;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Proverb> proverbs;
    static int nextId = 1;
    public static void buildJson(){
    }
    public static void deleteProverb(String idxStr){

        int deleteId = Integer.parseInt(idxStr); // throws Exception, add handling later
        if (proverbs.removeIf(proverb -> proverb.getId() == deleteId)){
            System.out.println(deleteId + "번 명언이 삭제되었습니다.");
        }
        else {
            System.out.println(deleteId + "번 명언은 존재하지 않습니다.");
        }
    }

    public static void addProverb(Scanner sc){
        System.out.print("명언 : ");
        String proverb = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();
        proverbs.add(new Proverb(proverb, author, nextId++));
        System.out.println(Integer.toString(nextId-1) + "번 명언이 등록되었습니다.");
    }

    public static void modifyProverb(Scanner sc, String idxStr) {
        int modifyId = Integer.parseInt(idxStr); // throws Exception, add handling later
        Proverb to_modify = null;
        for (Proverb proverb : proverbs) {
            if (proverb.getId() == modifyId) {
                to_modify = proverb;
            }
        }
        if (to_modify == null) {
            System.out.println(modifyId + "번 명언은 존재하지 않습니다.");
        } else {
            System.out.println("명언(기존) :" + to_modify.getProverb());
            String newProverb = sc.nextLine();
            System.out.println("작자(기존) :" + to_modify.getAuthor());
            String newAuthor = sc.nextLine();
            proverbs.set(modifyId-1, new Proverb(newProverb, newAuthor, modifyId));
        }

    }
    public static void listProverb(){
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (int i = proverbs.size()-1; i >= 0 ; i--) {
            System.out.printf("%d / %s / %s\n",
                    proverbs.get(i).getId(), proverbs.get(i).getAuthor(), proverbs.get(i).getProverb());
        }
    }

    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");
        proverbs = new ArrayList<>(); // Create an ArrayList object
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            if (cmd.equals( "빌드")) {
                buildJson();
            }
            if (cmd.length() > 6 && cmd.startsWith("삭제?id=")) {
                deleteProverb(cmd.substring(6));
            }
            if (cmd.length() > 6 && cmd.startsWith("수정?id=")) {
                modifyProverb(sc, cmd.substring(6));
            }
            if (cmd.equals( "등록")) {
                addProverb(sc);
            }
            if (cmd.equals( "목록")) {
                listProverb();
            }
            if (cmd.equals( "종료")) {
                sc.close();
                System.exit(0);
            }
        }
    }
}