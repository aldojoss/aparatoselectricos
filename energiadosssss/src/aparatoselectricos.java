public class aparatoselectricos {

    private String name;
    private int usodiario;
    private double volteos;
    private double consumomensual;
  

public aparatoselectricos(String name, int usodiario, double volteos, double consumomensual){
        this.name=name;
        this.usodiario=usodiario;
        this.volteos=volteos;
        this.consumomensual=consumomensual;
    }
    

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public int getUsodiario() {
    return usodiario;
}

public void setUsodiario(int usodiario) {
    this.usodiario = usodiario;
}

public double getConsumomensual() {
    return consumomensual;
}
public void setConsumomensual(double consumomensual) {
    this.consumomensual = consumomensual;
}

public double getVolteos() {
    return volteos;
}
public void setVolteos(double volteos) {
    this.volteos = volteos;
}
@Override
public String toString(){// este es un metodo para escribir los valores del objeto a string 
    return getName()+"---"+getUsodiario()+"---"+getVolteos()+"---"+getConsumomensual();
}



}
