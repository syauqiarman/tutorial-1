# **E-Shop Pemrograman Lanjut**
**Syauqi Armanaya Syaki**<br/>
**2206829010**<br/>
**Pemrograman Lanjut C**<br/>

# Module 1

**Reflection 1** <br>

Menurut saya beberapa prinsip clean code yang sudah saya gunakan adalah:

Meaningful names dimana saya sudah berusaha untuk menamakan variabel, function, dan file dengan nama yang mudah dipahami karena bersesuaian dengan kegunaan dan tujuannya, contohnya pada `findId()` pada ProductRepository.java yang jelas digunakan untuk mencari berdasarkan id nya. Function yang saya buat sudah menerapkan hal seperti ringkas, penamaan yang baik, danterorganisir dengan baik, sehingga terlihat jelas dan memiliki tujuannnya masing-masing. Adapun kekurangan yang saya lakukan seperti tidak memberikan comment pada kebanyakan kode yang saya buat, karena menurut saya dari penamaan variabel dan lain sebagainya saya rasa sudah jelas.

Adapun praktik dari secure coding yang tidak saya gunakan, salah satunya seperti validasi input khususnya pada quantity, dimana user bisa memasukkan input quantity dengan nilai negatif.

Lalu ada kesalahan saya pada saat edit product di awal, dimana saat mengedit maka nama productnya diint=put dari keadaan kosong, lalu saya membenarkan kode tersebut dengan cara membuat method findId untuk mengambil data product tersebut.

**Reflection 2** <br>

1. Setelah membuat unit test, saya merasa banyak belajar hal-hal baru, dan menurut saya pada tugas ini minimal kita membuat unit test untuk product yang exist dan yang tidak exist sudahlah cukup, karena sudah mewakili setiap function dan method yang saya buat. Namun, pendapat saya mungkin berbeda dengan yang lain, mungkin saja terdapat case lain yang tidak terdeteksi oleh saya dalam membuatnya. Tapi menurut saya semakin banyak test yang menghandle case yang ada maka akan semakin besar code coverage pada unit testnya dan akan mengurangi kemungkinan bug atau error pada kode. 

2. Menurut saya kode tersebut akan tidak clean jika hanya untuk memeriksa banyaknya item, karena kedua functional test di kasus tersebut tidak jauh berbeda dan masih menggunakan setup procedures dan instance variable yang sama, sehingga terkesan membuat kode yang sama tapi diulang berkali-kali. Permasalahan yang bisa diberikan contohnya seperti kasus repetitif saat ada yang berubah di html. Dari hal itu maka kita harus mengganti keduanya, padahal isinya mirip, jadi seperti buang-buang waktu. Sebaiknya kedua fungsi yang ada digabungkan saja, sehingga akan mempermudah maintenancenya dan setup yang dibuat tidak repetitif dan keseluruhan kode terlihat lebih clean.

# Module 2

**Reflection** <br>

Deployment Link: [Eshop](https://tutorial-prolan-syauqiarman.koyeb.app/)

1. List of code quality issue(s):
    - Menghilangkan modifier `public` pada beberapa test.
    - Pada file `ProductRepositoryTest.java` saya menukar posisi assertequals, karena sebaiknya yang di kiri sebagai expected dan kanan actual untuk menghindari ambiguitas.
    - Pada `EshopApplicationTest.java` saya mencoba untuk menambahkan assert agar test tersebut lebih optimal.
    - Mendefinisikan variabel constant untuk return redirect *url* pada `ProductController.java` karena dipakai berulang.

2. Menurut saya, implementasi yang telah saya lakukan pada workflows telah memenuhi CI/CD, karena Continuous Integration yang ada mencakup code dan testnya, selain itu, Continous Delivery telah menangani bagiannya dalam deployment. Adapun workflows yang menangani CI/CD, yakni `ci.yml` untuk melakukan automatic testing ketika push dan pull request ke salah satu branch. Lalu `scorecard.yml` dan `sonarcloud.yml` digunakan untuk mengecek kebersihan dari kode yang ada. Selain itu, saya menggunakan Koyeb PaaS untuk proses deployment yang didalamnya juga sudah mengimplementasikan CI/CD bawaan untuk memproses push dan pull request branch tertentu.

# Module 3

**Reflection** <br>

1. SOLID Principles yang sudah diterapkan:
* Single Responsibility Principle (SRP)
Saya melakukan pemisahan antara `CarController` dan `ProductController` agar dalam satu file hanya ada satu class controller. Lalu saya melakukan simplifikasi pada method update di file `CarRepository` dengan mengganti penggunaan loop untuk mencari car yang sesuai dengan yang ingin diubah menjadi menggunakan method `FindById` yang ada untuk memanfaatkannya.
* Interface Segregation Principle (ISP)
Pada dasarnya ISP adalah prinsip dimana interface sebaiknya tidak boleh terlalu besar dan harus dipisah menjadi beberapa bagian yang spesifik. Saya malakukan pemisahan antara interface `CarService` dan `ProductService` karena memiliki sifat yang berbeda. Jika saya menggabungkannya, maka dikhawatirkan interface akan memiliki beban yang besar, dimana sebenarnya tidak dari semua method itu diperlukan saat implementasinya, dikarenakan perbedaan sifatnya.
* Dependency Inversion Principle (DIP)
Saya melakukan prinsip DIP pada file `CarController` dengan mengubah penggunaan konkret dari `CarServiceImpl` dengan interface `CarService` untuk mencegah hal yang tidak diinginkan ketika saya mengubah sesuatu di `CarServiceImpl`.

2. Keuntungan menerapkan SOLID principles:
Saya merasa dengan menerapkan SOLID principles seperti contohnya SRP dan DIP yang memisahkan beberapa module sesuai dengan sifatnya, maka kode akan terlihat menjadi lebih bersih, rapi, dan mudah dibaca sehingga jika suatu hari ingin dilakukan perbaikan atau dikembangkan akan sangat mempercepat dan memudahkan. Contohnya yakni saat kita ingin melakukan pengubahan pada file `CarController` maka kita hanya akan bergantung dengan interface `CarService` dibandingkan dengan modul yang konkret. Selain itu, kode juga menjadi lebih fleksibel atas segala perubahan karena terfokus pada abstraksi dan berusaha untuk memisahkan ketergantungan antar kode, seperti pada file `CarServic`e dan `ProductService` yang memiliki sifat yang berbeda, sehingga lebih baik dipisahkan dibanding harus disatukan, karena tidak semua implementasi didalamnya akan digunakan.

3. Kekurangan yang dirasakan jika tidak menerapkan SOLID principles:

Jika tidak mengimplementasikan SOLID principles, maka akan terdapat banyak kerugian. Contohnya seperti sulitnya memelihara kode, karena kode yang ada sulit dibaca dan dimengerti yang tentunya akan menghambat pekerjaan. Selain itu, kita menjadi khawatir atas apa yang kita lakukan pada kode tersebut, karena pastinya akan berdampak pada kode-kode lainnya, karena banyaknya keterkaitan antara kode satu dan kode lainnya dan akan menjadi permasalahan baru. Contohnya jika di `CarController` masih bergantung pada `CarServiceImpl`, maka setiap ada perubahan di `CarServiceImpl` akan berdampak langsung dan mengakibatkan kerusakan pada `CarController`.

# Module 4

**Reflection** <br>

1. Bagi saya, metode TDD sangat bermanfaat karena memberikan struktur yang jelas dalam melakukan pengembangan perangkat lunak. Dalam metode TDD, langkah-langkahnya terbagi menjadi tiga fase, yaitu [RED], [GREEN], dan [REFACTOR]. Dengan pendekatan ini, saya dapat lebih mudah mengimplementasikan kelas karena semua kasus uji telah dipertimbangkan, baik yang positif maupun yang negatif. Sehingga memastikan bahwa kode yang dihasilkan lebih andal dan mempermudah proses penyempurnaan selanjutnya.

2. Dalam tutorial ini, unit test yang telah saya buat telah memenuhi prinsip F.I.R.S.T karena algoritma yang telah dirancang khusus untuk suatu metode dan tidak menyentuh metode lainnya. Pada pengujian layanan, saya juga menggunakan mock untuk memastikan bahwa tidak ada pengaruh pada kode aslinya. Selain itu, saya telah menambahkan asersi pada setiap hasil pengujian untuk memeriksa semua kemungkinan dengan baik. Setiap pengujian juga dirancang dengan cermat untuk mencakup semua skenario "happy" dan "unhappy" yang mungkin terjadi pada metode yang diuji.