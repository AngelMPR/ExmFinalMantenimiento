import java.util.HashMap;

public class RentalInfo {

  public String statement(Customer customer) {
    HashMap<String, Movie> movies = new HashMap();
    movies.put("F001", new Movie("You've Got Mail", "regular"));
    movies.put("F002", new Movie("Matrix", "regular"));
    movies.put("F003", new Movie("Cars", "childrens"));
    movies.put("F004", new Movie("Fast & Furious X", "new"));

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    String result = "Rental Record for " + customer.getName() + "\n";
    for (MovieRental r : customer.getRentals()) {
      String movieCode = movies.get(r.getMovieId()).getCode();
      double thisAmount = calculateAmount(movieCode, r.getDays());
      int bonusPoints = calculateBonusPoints(movieCode, r.getDays());

      //print figures for this rental
      result += "\t" + movies.get(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
      totalAmount += thisAmount;
      frequentEnterPoints += bonusPoints;
    }
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentEnterPoints + " frequent points\n";

    return result;
  }

  private double calculateAmount(String movieCode, int days) {
    double amount = 0;
    if (movieCode.equals("regular")) {
      amount = 2;
      if (days > 2) {
        amount += (days - 2) * 1.5;
      }
    } else if (movieCode.equals("new")) {
      amount = days * 3;
    } else if (movieCode.equals("childrens")) {
      amount = 1.5;
      if (days > 3) {
        amount += (days - 3) * 1.5;
      }
    }
    return amount;
  }

  private int calculateBonusPoints(String movieCode, int days) {
    int bonusPoints = 1;
    if (movieCode.equals("new") && days > 2) {
      bonusPoints++;
    }
    return bonusPoints;
  }
}
