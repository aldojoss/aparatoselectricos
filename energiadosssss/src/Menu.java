import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Menu {

    public Scanner leer = new Scanner(System.in);
    private ArrayList<aparatoselectricos> aparatos= new ArrayList<>();

     public Menu (){
        archivopasaarraylist();
    } // este constructor nos permite q cuando se inicie el programa se pasen todos la lista de archivos al arraylist, pq al salir del programa
    //el arraylist se borra pero la lista no entonces, al entrar los valores q agregamos al archivo ps pasarlos al arraylist


    //oe no te asustes jaja ahi la logica es que al inicio vo sabes q cuando se sale del programa el arraylist no se guarda solo los archivos tons hay un metodo ahi
    //q se llama escrbiir de archivo al arraylist y lo oongo en el constructor para q cuando lo instacie ps se guarde lo q estaba en el archivo al arraylist
    

  
     public void agggprodarray() throws IOException{
        String name;
        int usodario=0;
        double volteos=0.0;
        double consumodiario=0.0;
        double consumomensual=0.0;
        
                System.out.println("Ingrese el nombre del producto que desea registrar");
                  name = leer.nextLine();
                  
        while (true) {
            try {
                System.out.println("Cuantas horas diarias suele usar su "+name+"?");
                usodario= leer.nextInt();
                break;

            } catch (Exception e) {
                System.out.println(e);
                leer.nextLine();
            }
        }

       
                 
          while (true) {
            try {
                System.out.println("Revise su producto y digite los volteos que tiene");
                 volteos=leer.nextDouble();
                 break;
            } catch (Exception e) {
                System.out.println(e);
                leer.nextLine();
            }
          }

                
                  consumodiario = (volteos / 1000.0) * usodario;
                  consumomensual = consumodiario * 30 * 6.446;

                  aparatos.add(new aparatoselectricos(name, usodario, volteos, consumomensual));
                  escribirarrayenarchivo();

        

    }

     public void escribirarrayenarchivo() throws IOException{

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("aparatos.txt"))) {
            for (aparatoselectricos aparatoselectricos2 : aparatos) {
                bw.write(aparatoselectricos2.toString());
                bw.newLine(); //peque;o buffer
            }
            System.out.println("Archivo guardado correctamente");
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

     public void leerarraydearchivo(){
       
        try (BufferedReader br = new BufferedReader(new FileReader("aparatos.txt"))) {
            String line;
            while ((line=br.readLine())!=null) {
                System.out.println(line);
            }
            
        } catch (IOException e) {
            e.printStackTrace();// corregir leer
        }
    }

     public void eliminararrayyarchivo() throws IOException{
        int opcion=0;
       if (aparatos.isEmpty()) {
        System.out.println("No hay aparatos electricos disponibles para eliminar");
        return;
       }
         

         while (opcion<1||opcion>aparatos.size()) {
            do {
                System.out.println("Ingrese el aparato electrico que desea eliminar");
            for(int i=0; i<aparatos.size();i++){
            System.out.println("---------------------------------------------------------------------------");
            System.out.println((i+1)+"-"+aparatos.get(i).getName());
            System.out.println("---------------------------------------------------------------------------");
         }
         //pedir el producto a eliminar aqui solo vamos a eliminar el del arraylist luego q eliminamos el del arraylist volvemos a sobreescribir la lista desde 0 con los valores actu
         
            try {
                System.out.print("Elija una opcion --->");
                 opcion = leer.nextInt();
                 break;
           
            } catch (Exception e) {
   
               System.out.println("Opcion Incorrecta");
               leer.nextLine();
               
            }
         } while (true);
         }
            
                aparatoselectricos apa = aparatos.remove(opcion-1);
                System.out.println("Producto "+apa.getName()+" Correctamente Eliminado");
                escribirarrayenarchivo();
                
             }
         
     public void archivopasaarraylist(){
       
        try (BufferedReader br = new BufferedReader(new FileReader("aparatos.txt"))) {
            String line;

            while ((line=br.readLine())!= null) {
                String []dividir = line.split("---");

                if (dividir.length==4) {
                    String nombre= dividir[0].trim();
                    int usodiario = Integer.parseInt(dividir[1].trim());
                    double volteos = Double.parseDouble(dividir[2].trim());
                    double consumomensual= Double.parseDouble(dividir[3].trim());

                    aparatos.add(new aparatoselectricos(nombre, usodiario, volteos, consumomensual));

                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        
     }  

     public void Interfaz (){
        boolean salir = false;
        do {
            try {
                   System.out.println("-----BIENVENIDO A ELECTROCOST-----");
                   System.out.println("1- Agrega un aparato electrico");             
                   System.out.println("2- Ver Consumo mensual mis aparatos electricos"); 
                   System.out.println("3- Modificar aparato electrico");              
                   System.out.println("4- Eliminar aparato electrico");  
                   System.out.println("5- Mostrar mis aparatos disponibles");                 
                   System.out.println("6- Salir");
                   System.out.print(" Elija una opcion--> ");
            
                   int opcion = leer.nextInt();

                   leer.nextLine();//buffer

                   switch (opcion) {
                        case 1:
                        agggprodarray();
                        break;
                        case 2:
                        consumototaldetodoslosaparatos();
                        break;
                        case 3:
                        modificaraparatos();
                        break;
                        case 4:
                        eliminararrayyarchivo();
                        break;
                        case 5:
                        mostraraparatos();
                        break;
                        case 6:
                        System.out.println("Gracias por usar ELECTROCOST");
                        salir=true;
                        break;
                   
                    default:
                        break;
                   }
                
            } catch (Exception e) {
               System.out.println(e);
               leer.nextLine();
            }
           } while (!salir);
     }

     public  double calcularconsumodetodoslosaparatos(){

        double consumototal=0.0;

       for (aparatoselectricos aparatoselectricos2 : aparatos) {

          consumototal+=aparatoselectricos2.getConsumomensual();
       }

        return consumototal;
     }
        
     public void consumototaldetodoslosaparatos(){
        if (aparatos.isEmpty()) {
            System.out.println("No hay aparatos en tu lista");
            System.out.println("!Agrega aparatos electricos para poder ver su consumo !");
            return;
        }

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("APARATOS ELECTRICOS - CONSUMO MENSUAL ");
        System.out.println("----------------------------------------------------------------------------------------------------");
       for (aparatoselectricos aparatoselectricos2 : aparatos) {
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println(aparatoselectricos2.getName()+" <--Su consumo mensual es de --> C$"+ aparatoselectricos2.getConsumomensual());
        System.out.println("----------------------------------------------------------------------------------------------------");
       }
       System.out.println("----------------------------------------------------------------------------------------------------");
       System.out.println("-CONSUMO MENSUAL-");
       System.out.println("----------------------------------------------------------------------------------------------------");
       double consumototaldetodoslosaparatos= calcularconsumodetodoslosaparatos();
       System.out.println("El consumo mensual de todos los aparatos electricos de la lista es de C$"+ consumototaldetodoslosaparatos);
       System.out.println("----------------------------------------------------------------------------------------------------");

    }

     public void modificaraparatos() throws IOException{


        if (aparatos.isEmpty()) {
            System.out.println("No hay aparatos en tu lista");
            System.out.println("!Agrega aparatos electricos para poder modificarlos !");
            return;
        }


        int opcion=0;
        int op=0;
        String namen;


        while (opcion<1||opcion>aparatos.size()) {//ingresar el aparato q quiere modificar
            do {
                try {
                    
                    System.out.println("Ingrese el aparato electrico que desea modificar");
                    for (int i=0; i<aparatos.size();i++){
                        System.out.println("------------------------------------------------");
                        System.out.println((i+1)+"-"+aparatos.get(i).getName());
                        System.out.println("------------------------------------------------");
                    }
                    System.out.print("Elija una opcion -->");
                    opcion = leer.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println(e);
                    leer.nextLine();
            }
            } while (true);
    }
    //ya leimos el aparato que quiere modificar

       while (op<1||op>3) {//leemos el atributo del aparato que desea modificar
           try {
            System.out.println("Ingrese los atributos del aparato que desea modificar");
            System.out.println("1- Nombre");
            System.out.println("2- Uso diario");
            System.out.println("3- Volteos del aparato");
            System.out.print("Elija una opcion -->");
            op=leer.nextInt();
             } catch (Exception e) {
             System.out.println(e);
             leer.nextLine();
               }
             }
        leer.nextLine();
        if (op==1) {//leer el nuevo nombre
            System.out.println("Ingrese el nuevo nombre del aparato "+ aparatos.get(opcion-1).getName());
            namen =leer.nextLine();
            aparatos.get(opcion-1).setName(namen);
        }

        if (op==2) {
            while (true) {
                try {
                    System.out.println("Ingrese el uso diario modificado de "+aparatos.get(opcion-1).getName());
            int usodiario= leer.nextInt();
            aparatos.get(opcion-1).setUsodiario(usodiario);
            break;
                } catch (Exception e) {
                    System.out.println(e);
                    leer.nextLine();
                }
            }

            
        }

        if (op==3) {//leer el nuevo volteo
            while (true) {
                try {
                    System.out.println("Ingrese el nuevo valor de volteos que tendra el aparato "+ aparatos.get(opcion-1).getName());
                    double volti = leer.nextDouble();
                    aparatos.get(opcion-1).setVolteos(volti);
                    break;
                } catch (Exception e) {
                    System.out.println(e);
                    leer.nextLine();
                }
            }
        }
    escribirarrayenarchivo();
    }
   
     public void mostraraparatos(){
        if (aparatos.isEmpty()) {
            System.out.println("No hay aparatos en tu lista");
            System.out.println("!Agrega aparatos electricos para poder verlos !");
            return;
        }

        System.out.println("APARATOS DISPONIBLES DE TU LISTA");
        System.out.println("              |                  ");
        System.out.println("              V                  ");;
        for(int i=0 ;i<aparatos.size();i++){
            System.out.println("--------------------------------");
            System.out.println((i+1)+"--"+aparatos.get(i).getName());
            System.out.println("--------------------------------");
        }
    }
    





    
    public ArrayList<aparatoselectricos> getAparatos() {
        return aparatos;
    }

    public void setAparatos(ArrayList<aparatoselectricos> aparatos) {
        this.aparatos = aparatos;
    }
    
}

    

