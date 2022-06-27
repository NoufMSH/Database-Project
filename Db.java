
package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author raghad
 */
public class Db {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection myconobj = null;
        Statement mystsobj = null;
        ResultSet myresobj = null;
        String query = "";
        boolean newRec = false;
        boolean done = true;
        boolean flag2 = false;
        Scanner input = new Scanner(System.in);
        char ch='4';
        boolean newRec2=false;
        try {
            myconobj = DriverManager.getConnection("jdbc:derby://localhost:1527/is230", "Alya", "0535056377");
            mystsobj = myconobj.createStatement();
  mystsobj = myconobj.createStatement();

            while (true){
                if(newRec2==false){
                System.out.println("Choose a number");
                System.out.println("Table EQUIPMENT");
                System.out.println("(1) for inserting a record \n(2) for deleting a record\n(3) for updating a record \n(4) for exit");
                ch = input.next().charAt(0);}
                switch (ch) {
                    case '1': 
        try{
    do{
   do{  
     flag2=false;  
     System.out.println(" EQUIPMENT ID=");
      int id=input.nextInt(); 
      String id2=""+id;
      System.out.println("EQUIPMENT DESCRIPTION");
      input.nextLine(); 
      String DES=input.nextLine(); 
      String DES2=DES.equalsIgnoreCase("null")?"\'\'":"\'"+DES+"\'";
      
       System.out.println(" EQUIPMENT NAME");
       String NAME=input.next(); 
       NAME="\'"+NAME+"\'";
     
        System.out.println(" BRANCH ID=");
        int B_id=input.nextInt(); 
        int length = String.valueOf(id).length();

     if(length<=3){
 if(length<3)
 {
     for(int x=length;x<3;x++)
     id2+='0';
 }
 id=Integer.parseInt(id2);
 String q="Select branch_id from BRANCH ";
  myresobj=mystsobj.executeQuery(q);
int id_branch;
  while(myresobj.next()){
   id_branch=myresobj.getInt("branch_id");
     if(B_id==id_branch ||  B_id==0)//either null or an existing id branch
         flag2=true;
}
  if(flag2==false){
  System.out.print("Invalid  branch id it violates the forign key constrain");
  }
  else{
   String insertquery="insert into EQUIPMENT "+ "values ("+id+","+DES2+","+NAME+","+B_id+")"; 
   //  int countInserted2 = mystsobj.executeUpdate(ss);
     int countInserted = mystsobj.executeUpdate(insertquery);
     System.out.println(countInserted + " record inserted.\n");}
     newRec2=false;
     System.out.println("Insert New record (Y/N)\n?");
     char c=input.next().charAt(0);
     newRec= c=='y'|| c=='Y'?true:false;
     done=false;
    }
     else 
      System.out.print("Invalid  equpment id,Please enter a valid number of 3 digits ");
   }while(done);
    // myresobj=mystsobj.executeQuery(query);
    // while(myresobj.next())
   }while(newRec);
        }/////////////////end try 2
        catch (SQLIntegrityConstraintViolationException e) {
            System.out.print("violates the primary key constrain\n");
             newRec2=false;
            System.out.println("Insert New record (Y/N)\n?");
          char c2=input.next().charAt(0);
           newRec2= c2=='y'|| c2=='Y'?true:false;
        }
catch(SQLException e){
        e.printStackTrace();
}
        
catch (InputMismatchException e) {
 System.out.println(" you have given input as wrong type");
     newRec2=false;
            System.out.println("Insert New record (Y/N)\n?");
            input.nextLine();
          char c2=input.next().charAt(0);
           newRec2= c2=='y'|| c2=='Y'?true:false;
                            }
catch(Exception e){
        e.printStackTrace();
      //System.exit(0);

}
                break;    
//////////////////////////////////////////////////////////////////////////////////////////////////////end case
                    case '2':
                        
                        boolean delete = true;
                        try {
                        do{
                                System.out.println("Enter equipment ID you want to delete");
                                int ID = input.nextInt();
                                int DeleteCount = mystsobj.executeUpdate("delete from EQUIPMENT where ID=" + ID);
                                if (DeleteCount == 0) {
                                    System.out.println("This ID doesnt exist!");
                                }//end if
                                else {
                                    System.out.println(DeleteCount + " equipment deleted successfully");
                                }
                                while (true) {
                                System.out.println("Delete another record ( Y | N ) ?");
                                char d = input.next().charAt(0);
                                if (d == 'n' || d == 'N') {
                                        delete = false;
                                        break;
                                } else if (d == 'y' || d == 'Y') {
                                        delete = true;
                                        break;
                                    } else {
                                    System.out.println("invalid input");
                                }

                                }
                                }while (delete);
                        }//end try
                        catch (InputMismatchException ex) {
                            System.out.println(" you have given input as wrong type");
                            }
                        catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                        break;
                   case '3':
                        int count = 0;
                        boolean wantToUpdate = true;
                           try{
                        do {
                            System.out.println("Enter the EQUIPMENT ID");
                            int updateRow = input.nextInt();
                            String idVal = "" + updateRow;
                            int length = String.valueOf(updateRow).length();

                            if (length <= 3) {
                                if (length < 3) {
                                    for (int x = length; x < 3; x++) {
                                        idVal += '0';
                                    }
                                }
                            }
                            updateRow = Integer.parseInt(idVal);
                            String qID = "Select ID from EQUIPMENT ";
                            myresobj = mystsobj.executeQuery(qID);
                            int id_EQ;
                            boolean existRow = false; ////logic error 
                            while (myresobj.next()) {
                                id_EQ = myresobj.getInt("ID");
                                if (updateRow == id_EQ) {
                                    existRow = true;
                                }
                            }

                            if (existRow) {
                                System.out.println("What do you want to update,choose a number \n1.Equipment id\n2.Description\n3.Name\n4.Branch id");
                                int opt = input.nextInt();
                                switch (opt) {
                                    case 1:
                                        System.out.println("Enter the new Equipment id");
                                        int newEquipmentId = input.nextInt();
                                        idVal = "" + newEquipmentId;
                                        length = String.valueOf(newEquipmentId).length();

                                        if (length <= 3) {
                                            if (length < 3) {
                                                for (int x = length; x < 3; x++) {
                                                    idVal += '0';
                                                }
                                            }
                                        }
                                        newEquipmentId = Integer.parseInt(idVal);
                                        String qu = "Select ID from EQUIPMENT ";
                                        myresobj = mystsobj.executeQuery(qu);
                                        boolean notExistId = true;
                                        while (myresobj.next()) {
                                            id_EQ = myresobj.getInt("ID");
                                            if (newEquipmentId == id_EQ) {
                                                notExistId = false;
                                            }
                                        }
                                        if (notExistId) {
                                            String q1 = "UPDATE EQUIPMENT SET ID = " + newEquipmentId + "WHERE ID =" + updateRow;
                                            int r1 = mystsobj.executeUpdate(q1);
                                            r1 += count++;
                                            System.out.println(r1 + " record updated");
                                        } else {
                                            System.out.println("the ID you entered is taken, try again");
                                        }
                                        break;

                                    case 2:
                                        System.out.println("enter the new Description");
                                        input.nextLine();
                                        String newDES = input.nextLine();
                                        String newDES2 = newDES.equalsIgnoreCase("null") ? "\'\'" : "\'" + newDES + "\'";
                                        if (newDES2.length() <= 150) {
                                            String q2 = "UPDATE EQUIPMENT SET  description =" + newDES2 + "WHERE ID =" + updateRow;
                                            int r2 = mystsobj.executeUpdate(q2);
                                            r2 += count++;
                                            System.out.println(r2 + "row updated");
                                        } else {
                                            System.out.println("the Description you entered is too long,acceptaple length is 150");
                                        }
                                        break;

                                    case 3:
                                        System.out.println("enter the new Name");
                                        input.nextLine();
                                        String newName = input.nextLine();

                                       if (newName.equals("")) {
                                            System.out.println("the Name can not be null");
                                        } else {
                                            newName = "\'" + newName + "\'";
                                            if (newName.length() <= 30) {
                                                String q3 = "UPDATE EQUIPMENT SET  name =" + newName + "WHERE ID =" + updateRow;
                                                int r3 = mystsobj.executeUpdate(q3);
                                                r3 += count++;
                                                System.out.println(r3 + "row updated");
                                            } else {
                                                System.out.println("the Name you entered is too long,acceptaple length is 30");
                                            }
                                        }

                                        break;
                                    case 4:
                                        System.out.println("enter the new Branch id");
                                        int newBranchId = input.nextInt();
                                        boolean validFK = false;
                                        qu = "Select branch_id from BRANCH ";
                                        myresobj = mystsobj.executeQuery(qu);
                                        int id_br;
                                        while (myresobj.next()) {
                                            id_br = myresobj.getInt("branch_id");
                                            if (newBranchId == id_br) {
                                                validFK = true;
                                            }
                                        }
                                        if (validFK == false) {
                                            System.out.print("Invalid  branch it violates the forign key constrain");
                                        } else {

                                            String q4 = "UPDATE EQUIPMENT SET branchID =" + newBranchId + "WHERE ID =" + updateRow;
                                            int r4 = mystsobj.executeUpdate(q4);
                                            r4 += count++;
                                            System.out.println(r4 + "row updated");
                                        }
                                        break;

                                    default:
                                        System.out.println("invalid choice");
                                }//end switch
                            

                                while (true) {
                                    System.out.println("update another new record?(yes/no)");
                                    String anotherRow = input.next();
                                    if (anotherRow.equalsIgnoreCase("no")) {
                                        wantToUpdate = false;
                                        break;
                                    } else if (anotherRow.equalsIgnoreCase("yes")) {
                                        wantToUpdate = true;
                                        break;
                                    } else {
                                        System.out.println("please just enter (yes/no)");
                                    }

                                }

                            }//end exist if
                           
                            else {
                                System.out.println("the ID you entered is not exist, try again");
                            }
                            
                          } while (wantToUpdate);
                           }///end try
                            catch (InputMismatchException ex) {
                            System.out.println(" you have given input as wrong type");
                            }


                        break;
                    case '4':
                        System.exit(0);
                        break;

                    default:
                        System.out.println("please choose a number withn the range");

                }//end switch 

            }//end while 

        }//end try
        catch (SQLIntegrityConstraintViolationException e) {
            System.out.print("violates the primary key constrain");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                myconobj.close();
                mystsobj.close();
                myresobj.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }//end finally

    }//end main
    }//end clas db