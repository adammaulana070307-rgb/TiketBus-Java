/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tiketbus;
import java.util.Scanner;

// INTERFACE
interface TicketInfo {
    void showTicket();
}

// ABSTRACT CLASS
abstract class Person {
    private String name;
    private String phone;

    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    // ENKAPSULASI
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }

    // ABSTRACT METHOD
    public abstract void displayInfo();
}

// INHERITANCE
class Passenger extends Person {
    private String destination;

    public Passenger(String name, String phone, String destination) {
        super(name, phone);
        this.destination = destination;
    }

    public String getDestination() { return destination; }

    // OVERRIDING
    @Override
    public void displayInfo() {
        System.out.println("Nama Penumpang : " + getName());
        System.out.println("No HP          : " + getPhone());
        System.out.println("Tujuan         : " + destination);
    }
}

// CLASS ADMIN
class Admin extends Person {
    private String adminCode;

    public Admin(String name, String phone, String adminCode) {
        super(name, phone);
        this.adminCode = adminCode;
    }

    @Override
    public void displayInfo() {
        System.out.println("Admin          : " + getName() + " (" + adminCode + ")");
        System.out.println("Kontak Admin   : " + getPhone());
    }

    // METHOD KONFIRMASI
    public void confirmBooking(Ticket ticket, int jumlahTiket) {
        System.out.println("\n===== KONFIRMASI ADMIN =====");
        System.out.println("Pemesanan tiket berhasil diproses");
        System.out.println("Jumlah Tiket   : " + jumlahTiket);
        displayInfo();
        System.out.println("Silakan lakukan pembayaran");
        System.out.println("============================");
    }
}

// CLASS BUS
class Bus {
    private String busName;
    private String route;
    private double price;

    public Bus(String busName, String route, double price) {
        this.busName = busName;
        this.route = route;
        this.price = price;
    }

    public String getBusName() { return busName; }
    public String getRoute() { return route; }
    public double getPrice() { return price; }

    public void showBusInfo() {
        System.out.println("Nama Bus : " + busName);
        System.out.println("Rute     : " + route);
        System.out.println("Harga    : Rp " + price);
    }
}

// IMPLEMENT INTERFACE
class Ticket implements TicketInfo {
    private Passenger passenger;
    private Bus bus;
    private String ticketNumber;

    public Ticket(String ticketNumber, Passenger passenger, Bus bus) {
        this.ticketNumber = ticketNumber;
        this.passenger = passenger;
        this.bus = bus;
    }

    // OVERLOADING
    public void calculatePrice() {
        System.out.println("Total Harga : Rp " + bus.getPrice());
    }

    public void calculatePrice(int quantity) {
        System.out.println("Total Harga : Rp " + (bus.getPrice() * quantity));
    }

    @Override
    public void showTicket() {
        System.out.println("\n===== TIKET BUS =====");
        System.out.println("Nomor Tiket : " + ticketNumber);
        passenger.displayInfo();
        bus.showBusInfo();
        System.out.println("=====================");
    }
}

// MAIN CLASS
public class TiketBus {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // ADMIN
        Admin admin = new Admin("Rahmat", "08123456789", "ADM-01");

        // DATA BUS
        Bus bus1 = new Bus("Harapan Jaya", "Malang - Surabaya", 50000);
        Bus bus2 = new Bus("Titian Mas", "Malang - Jakarta", 250000);
        Bus bus3 = new Bus("Dunia Mas", "Malang - Bandung", 200000);
        Bus bus4 = new Bus("Surya Kencana", "Malang - Bima", 650000);

        // TAMPILKAN DAFTAR BUS
        System.out.println("===== DAFTAR BUS =====");
        System.out.println("\n1. Harapan Jaya (Malang - Surabaya) - Rp 50000");
        System.out.println("2. Titian Mas (Malang - Jakarta) - Rp 250000");
        System.out.println("3. Dunia Mas (Malang - Bandung) - Rp 200000");
        System.out.println("4. Surya Kencana (Malang - Bima) - Rp 650000");

        // PILIH BUS
        System.out.print("\nPilih Bus : ");
        int pilih = input.nextInt();
        input.nextLine();

        Bus selectedBus;
        switch (pilih) {
            case 1: selectedBus = bus1; break;
            case 2: selectedBus = bus2; break;
            case 3: selectedBus = bus3; break;
            case 4: selectedBus = bus4; break;
            default:
                selectedBus = bus1;
                System.out.println("Pilihan tidak tersedia! Otomatis memilih bus 1.");
        }

        // INPUT DATA PENUMPANG
        System.out.print("\nMasukkan Nama Penumpang : ");
        String nama = input.nextLine();

        System.out.print("Masukkan Nomor HP : ");
        String hp = input.nextLine();

        System.out.print("Jumlah Tiket : ");
        int jumlah = input.nextInt();

        // OBJECT PENUMPANG & TIKET
        Passenger passenger = new Passenger(nama, hp, selectedBus.getRoute());
        Ticket ticket = new Ticket("TKT001", passenger, selectedBus);

        // OUTPUT UTAMA
        ticket.showTicket();
        ticket.calculatePrice(jumlah);
        admin.confirmBooking(ticket, jumlah);

        // TAMBAHAN SEDIKIT UNTUK MENUTUP RUANG KOSONG DI TERMINAL
        System.out.println("\n====================================");
        System.out.println("  TERIMA KASIH ATAS KUNJUNGAN ANDA  ");
        System.out.println("    UTAMAKAN KESELAMATAN BERKENDARA   ");
        System.out.println("====================================");

        input.close();
    }
}
