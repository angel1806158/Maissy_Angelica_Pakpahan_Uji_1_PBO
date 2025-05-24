//SOAL 2 SISTEM KASIR

import java.util.*;

//abstract class product: menjadi blueprint umum untuk semua produk.
//menyediakan struktur dasar berupa nama, harga, dan metode abstract getTotalPrice.
abstract class Product {
    protected String name;
    protected double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    //method abstract yang akan diimplementasikan berbeda di setiap subclass
    public abstract double getTotalPrice(int quantity);

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

//interface taxable: digunakan untuk produk yang dikenakan pajak
interface Taxable {
    double TAX_RATE = 0.1;

    double calculateTax(double amount);
}

//subclass food: turunan dari product
class Food extends Product implements Taxable {
    public Food(String name, double price) {
        super(name, price);
    }

    @Override
    public double calculateTax(double amount) {
        return amount * TAX_RATE;
    }

    @Override
    public double getTotalPrice(int quantity) {
        double subtotal = price * quantity;
        return subtotal + calculateTax(subtotal);
    }
}

//subclass beverage: turunan dari product dan juga mengimplementasikan interface taxable
class Beverage extends Product implements Taxable {
    public Beverage(String name, double price) {
        super(name, price);
    }

    //mengimplementasikan metode dari interface taxable
    @Override
    public double calculateTax(double amount) {
        return amount * TAX_RATE;
    }

    @Override
    public double getTotalPrice(int quantity) {
        double subtotal = price * quantity;
        return subtotal + calculateTax(subtotal);
    }
}

public class Kasir {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //array produk: menggunakan tipe abstrak poduct (polymorphism)
        Product[] products = {
            new Food("Nasi goreng", 18000),
            new Food("Mie ayam", 12000),
            new Food("Bakso", 15000),
            new Beverage("Kopi susu", 10000),
            new Beverage("Es teh", 5000),
            new Beverage("Air mineral", 3000)
        };

        List<Product> cart = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();

        System.out.println("=== Selamat Datang di Pembayaran Kasir ===");

        while (true) {
            System.out.println("\nDaftar Produk:");
            for (int i = 0; i < products.length; i++) {
                System.out.printf("%d. %s - Rp %.0f\n", i + 1, products[i].getName(), products[i].getPrice());
            }
            System.out.println("0. Selesai dan Bayar");

            System.out.print("Pilih produk (nomor): ");
            int choice = scanner.nextInt();

            if (choice == 0) break;

            if (choice < 1 || choice > products.length) {
                System.out.println("Pilihan tidak valid.");
                continue;
            }

            System.out.print("Masukkan jumlah: ");
            int quantity = scanner.nextInt();

            cart.add(products[choice - 1]);
            quantities.add(quantity);
        }

        double grandTotal = 0;
        System.out.println("\n=== Ringkasan Belanja ===");

        //polymorphism digunakan di sini:
        //meski semua item bertipe Product, method getTotalPrice akan memanggil implementasi yang sesuai (food atau beverage)
        for (int i = 0; i < cart.size(); i++) {
            Product item = cart.get(i);
            int qty = quantities.get(i);
            double total = item.getTotalPrice(qty); //polymorphic behavior
            System.out.printf("%s x%d - Total: Rp %.0f\n", item.getName(), qty, total);
            grandTotal += total;
        }

        System.out.printf("\nTotal Pembayaran: Rp %.0f\n", grandTotal);
        System.out.println("Terima kasih telah berbelanja!");

        scanner.close();
    }
}
