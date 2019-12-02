package advent.of.code.a2018.day13;

import java.util.List;

public class Voiture implements Comparable<Voiture>{

  private Position position;
  
  private char direction;
  
  private int nbTurn = 0;

  public Voiture(int x, int y , char direction) {
    this.position = new Position(x, y);
    this.direction = direction;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public char getDirection() {
    return direction;
  }

  public void setDirection(char direction) {
    this.direction = direction;
  }
  
  public void run(List<Position> positions) {
    updatePosition();
    updateDirection(positions);
  }

  private void updatePosition() {
    switch (direction) {
    case '^':
      position.setY(position.getY()-1);
      break;
    case 'v':
      position.setY(position.getY()+1);
      break;
    case '>':
      position.setX(position.getX()+1);
      break;
    case '<':
      position.setX(position.getX()-1);
      break;
    default:
      break;
    }
  }

  private void updateDirection(List<Position> positions) {
    char newP = positions.stream().filter(p -> position.equals(p)).map(Position::getC).findFirst().get();
    if(newP == '/' && direction == '^') {
      direction = '>';
    }
    else if(newP == '/' && direction == 'v') {
      direction = '<';
    }
    else if(newP == '/' && direction == '>') {
      direction = '^';
    }
    else if(newP == '/' && direction == '<') {
      direction = 'v';
    }
    else if(newP == '\\' && direction == '^') {
      direction = '<';
    }
    else if(newP == '\\' && direction == 'v') {
      direction = '>';
    }
    else if(newP == '\\' && direction == '>') {
      direction = 'v';
    }
    else if(newP == '\\' && direction == '<') {
      direction = '^';
    }else if(newP == '+') {
      if(nbTurn == 0) {
        switch (direction) {
        case '^':
          direction = '<';
          break;
        case 'v':
          direction = '>';
          break;
        case '<':
          direction = 'v';
          break;
        case '>':
          direction = '^';
          break;
        }
      }else if(nbTurn == 2) {
        switch (direction) {
        case '^':
          direction = '>';
          break;
        case 'v':
          direction = '<';
          break;
        case '<':
          direction = '^';
          break;
        case '>':
          direction = 'v';
          break;
        }
      }
      nbTurn = (nbTurn+1) % 3  ;
    }
  }

  @Override
  public int compareTo(Voiture o) {
    return position.compareTo(o.getPosition());
  }
}
