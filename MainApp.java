//SOAL 1

//abstract class vehicle: sebagai dasar semua jenis kendaraan
abstract class Vehicle {
    //method abstract: wajib diimplementasikan oleh turunan
    public abstract void startEngine();
}

//interface electric: hanya untuk kendaraan listrik
interface Electric {
    // Method untuk mengisi daya baterai
    void chargeBattery();
}

//class Car: mewarisi vehicle dan mengimplementasi electric
class Car extends Vehicle implements Electric {
    @Override
    public void startEngine() {
        System.out.println("[Mobil] Mesin dihidupkan secara otomatis dengan menekan tombol start.");
    }

    @Override
    public void chargeBattery() {
        System.out.println("[Mobil] Mengisi daya baterai... Mohon tunggu hingga penuh.");
    }
}

//class motorcycle: hanya mewarisi vehicle
class Motorcycle extends Vehicle {
    @Override
    public void startEngine() {
        System.out.println("[Motor] Mesin dinyalakan secara otomatis dengan menekan tombol start.");
    }
}

public class MainApp {
    public static void main(String[] args) {
        //polymorphism: objek kendaraan dengan tipe vehicle
        Vehicle vehicle1 = new Car();
        Vehicle vehicle2 = new Motorcycle();

        //menyalakan mesin untuk masing-masing kendaraan
        System.out.println("== Demonstrasi Menyalakan Mesin ==");
        vehicle1.startEngine();
        vehicle2.startEngine();

        //mengecek apakah kendaraan sudah mengisi atau belum mengisi daya
        System.out.println("\n== Cek Kendaraan Listrik ==");

        if (vehicle1 instanceof Electric) {
            Electric electricVehicle = (Electric) vehicle1;
            electricVehicle.chargeBattery();
        } else {
            System.out.println("[Mobil] Kendaraan ini tidak mendukung pengisian daya.");
        }

        if (vehicle2 instanceof Electric) {
            Electric electricVehicle = (Electric) vehicle2;
            electricVehicle.chargeBattery();
        } else {
            System.out.println("[Motor] Baterai penuh.. siap untuk digunakan.");
        }
    }
}
