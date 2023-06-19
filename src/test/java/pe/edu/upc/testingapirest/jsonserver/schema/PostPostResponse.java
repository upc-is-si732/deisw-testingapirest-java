package pe.edu.upc.testingapirest.jsonserver.schema;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class PostPostResponse {
  private int id;
  private String title;
  private String author;
}
