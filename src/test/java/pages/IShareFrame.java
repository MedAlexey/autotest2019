package pages;

public interface IShareFrame {

    // закрыть окно
    IProductPage closeFrame();

    // нажать кнопку "поделиться"
    IProductPage share();

    // написать текст
    void writeText(String text);

}
