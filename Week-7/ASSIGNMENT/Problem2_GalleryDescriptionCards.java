abstract class ArtPiece {
    private static int nextNumber = 0;
    private final String pieceId;

    public ArtPiece() {
        nextNumber++;
        pieceId = "ART-" + nextNumber;
    }

    public String getPieceId() {
        return pieceId;
    }

    public abstract String describe();
}

class Painting extends ArtPiece {
    private String title;

    public Painting(String title) {
        super();
        this.title = title;
    }

    @Override
    public String describe() {
        return "Painting: " + title + ", framed on canvas";
    }
}

class Sculpture extends ArtPiece {
    private String title;

    public Sculpture(String title) {
        super();
        this.title = title;
    }

    @Override
    public String describe() {
        return "Sculpture: " + title + ", carved from stone";
    }
}

public class Problem2_GalleryDescriptionCards {
    public static void main(String[] args) {
        Painting painting = new Painting("Sunset Fields");
        Sculpture sculpture = new Sculpture("The Thinker II");
        System.out.println(painting.describe());
        System.out.println(painting.getPieceId());
        System.out.println(sculpture.describe());
        System.out.println(sculpture.getPieceId());
    }
}
