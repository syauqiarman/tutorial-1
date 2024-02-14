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