package de.codeshelf.consoleui.prompt;

/**
 * Result of a list choice. Holds the id of the selected item.
 * <p>
 * Created by Andreas Wegmann on 03.02.16.
 */
public class ListAnswer implements Answer {

  String selectedId;

  /**
   * Returns the ID of the selected item.
   *
   * @return id of selected item
   */
  public String getSelectedId() {
    return selectedId;
  }

  /**
   * Default constructor.
   *
   * @param selectedId id of selected item.
   */
  public ListAnswer(String selectedId) {
    this.selectedId = selectedId;
  }

  @Override
  public String toString() {
    return "ListResult{" +
            "selectedId='" + selectedId + '\'' +
            '}';
  }
}
