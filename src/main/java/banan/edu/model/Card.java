package banan.edu.model;

public class Card {
    private int id;
    private Suit suit;
    private Denomination denomination;
    private int value;
    private String image;
    private int position;
    private int rotation;
    private int number;
    private int top;

    public Card() {
    }

    public Card(int id, Suit suit, Denomination denomination, int value, String image) {
        this.id = id;
        this.suit = suit;
        this.denomination = denomination;
        this.value = value;
        this.image = image;
    }

    public Card(int id, String image, int value) {
        this.id = id;
        this.image = image;
        this.value = value;
    }

    public Card(int id, Suit suit, Denomination denomination, int value, String image, int position, int rotation, int number,
    int top) {
        this.id = id;
        this.suit = suit;
        this.denomination = denomination;
        this.value = value;
        this.image = image;
        this.position = position;
        this.rotation = rotation;
        this.number = number;
        this.top = top;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public void setDenomination(Denomination denomination) {
        this.denomination = denomination;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", suit=" + suit +
                ", denomination=" + denomination +
                ", value=" + value +
                ", image='" + image + '\'' +
                '}';
    }
}
