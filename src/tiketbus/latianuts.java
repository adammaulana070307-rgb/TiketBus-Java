package tiketbus;

// ==================== 1. CLASS LINK ====================
class Link {
    public long ID_Laptop;       
    public double Speed_Mbps;    
    public Link next; 
    public Link previous;
    
    public Link(long id, double speed) {
        this.ID_Laptop = id; 
        this.Speed_Mbps = speed;
    }
    
    public void displayLink() {
        System.out.println(" " + ID_Laptop + ", speed " + Speed_Mbps + " Mbps");
    }
}

// ==================== 2. CLASS DOUBLY LINKED LIST ====================
class DoublyLinkedList {
    private Link first; 
    private Link last; 
    private int n = 0;
    
    public boolean isEmpty() { return first == null; }

    public void insertLast(long id, double speed) {
        Link newLink = new Link(id, speed); 
        if (isEmpty()) { 
            first = newLink; 
        } else { 
            last.next = newLink; 
            newLink.previous = last; 
        }
        last = newLink; 
        n++;
    }
    
    // Fungsi mean dan median dari kodingan aslimu
    public double getMean() {
        double sum = 0; Link curr = first;
        while (curr != null) { sum += curr.Speed_Mbps; curr = curr.next; }
        return sum / n;
    }

    public double getMedian() {
        Link kiri = first; Link kanan = last;
        while (kiri.next != null && kanan.previous != null) {
            if (kiri == kanan) { return kiri.Speed_Mbps; }
            else if (kiri.previous == kanan) { break; }
            kiri = kiri.next; kanan = kanan.previous;
        }
        return (kiri.Speed_Mbps + kanan.Speed_Mbps) / 2;
    }

    public int getPass(double batas) {
        int count = 0; Link curr = first;
        while (curr != null) {
            if (curr.Speed_Mbps >= batas) count++;
            curr = curr.next;
        }
        return count;
    }
}

// ==================== 3. MAIN METHOD UTAMA ====================
public class latianuts { 

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        
        // Memasukkan data dengan penanda huruf L besar (Tipe data long)
        list.insertLast(1765027L, 100.0);
        list.insertLast(1765009L, 95.6);
        list.insertLast(1765019L, 95.0);
        
        System.out.println("BUILD SUCCESSFUL!");
        System.out.println("mean    : " + list.getMean());
        System.out.println("median  : " + list.getMedian());
    }
}
