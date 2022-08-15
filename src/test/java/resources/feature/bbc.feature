Feature: Verification of Get call on Media EndPoint

  Scenario:Verify that the BBC  Music tracks application can be accessed by users
    When User sends a GET request to the media endpoints
    Then then user must get back response with status code 200

  Scenario:Verify that the BBC  Music tracks application  get call is under 1000 milliseconds
    When User sends a GET request to the media endpoints
    Then Response time should be below 1000 milliseconds

  Scenario: As  a user I should always presented with Music track id as not null or empty
    When User sends a GET request to the media endpoints
    Then Verify that the id "id" field is never null or Empty for all the items present in the list

  Scenario: As  a user I should always see segment_type value as music
    When User sends a GET request to the media endpoints
    Then the segment type "segment_type" field for every track is music "music"

  Scenario:As a user I should always see primary field not null or empty  under title_list
    When User sends a GET request to the media endpoints
    Then Verify that the primary "primary" field in the  title list "title_list" is never  null or empty for any track

  Scenario: As a user I should see only one track  field now_playing is true from  list under offset
    When User sends a GET request to the media endpoints
    Then Verify that only one track in the list has "now_playing" field in "offset" as true

  Scenario:As a user I should be present with date
    When User sends a GET request to the media endpoints
    Then Verify the "Date" value in the response headers


