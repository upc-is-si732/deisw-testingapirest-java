package pe.edu.upc.testingapirest.jsonserver.schema;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class PostPostRequest {

  private String title;
  private String author;
}
