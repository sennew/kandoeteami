package com.projects.wens.kandoeteami.session.data;

import com.projects.wens.kandoeteami.subthemes.data.SubTheme;
import com.projects.wens.kandoeteami.themes.data.Card;
import com.projects.wens.kandoeteami.themes.data.Theme;
import com.projects.wens.kandoeteami.user.data.User;

import java.util.List;

/*
{
   "sessionId":1,
   "sessionName":"First session name",
   "mode":"SYNC",
   "type":"IDEA",
   "state":"CREATED",
   "minCards":2,
   "maxCards":4,
   "size":6,
   "startTime":"2016-03-19T03:46:04.519",
   "endTime":"2016-04-01T12:00:00",
   "userAddCards":true,
   "themeId":null,
   "subThemeId":null,
   "chosenCards":false,
   "cards":[
      {
         "cardId":1,
         "description":"KdGCard longer description to check if everything works accordingly",
         "imageURL":"https://www.underconsideration.com/brandnew/archives/karel_de_grote_logo_detail.png",
         "themeId":1,
         "position":0,
         "links":[

         ]
      }
   ],
   "users":[
      {
         "userId":1,
         "username":"ArneLauryssens",
         "password":null,
         "oldPassword":null,
         "facebookAccount":false,
         "email":"arne.lauryssens@student.kdg.be",
         "roleTypes":[

         ],
         "profilePicture":"resources/images/users/1.jpg",
         "person":{
            "firstname":"Arne",
            "lastname":"Lauryssens",
            "address":{
               "street":"TestStreet",
               "number":"1",
               "zip":"2000",
               "city":"TestCity"
            },
            "links":[

            ]
         },
         "position":0,
         "links":[

         ]
      },
      {
         "userId":3,
         "username":"SenneWens",
         "password":null,
         "oldPassword":null,
         "facebookAccount":false,
         "email":"senne.wens@student.kdg.be",
         "roleTypes":[

         ],
         "profilePicture":"http://zblogged.com/wp-content/uploads/2015/11/c1.png",
         "person":{
            "firstname":"Senne",
            "lastname":"Wens",
            "address":{
               "street":null,
               "number":null,
               "zip":null,
               "city":null
            },
            "links":[

            ]
         },
         "position":1,
         "links":[

         ]
      }
   ],
   "theme":{
      "themeId":1,
      "themeName":"KdGTheme",
      "description":"KdG Theme description",
      "iconURL":"http://www.dandai.be/Resources/imgp1791.jpeg",
      "organisation":{
         "organisationId":1,
         "organisationName":"Karel De Grote",
         "address":"Groenplaats 5 2000 Antwerpen",
         "logoURL":"https://pbs.twimg.com/profile_images/664027982718177280/YUs5qbQb.png",
         "countUsers":0,
         "organiser":false,
         "links":[

         ]
      },
      "cards":[
         {
            "cardId":1,
            "description":"KdGCard longer description to check if everything works accordingly",
            "imageURL":"https://www.underconsideration.com/brandnew/archives/karel_de_grote_logo_detail.png",
            "themeId":1,
            "position":0,
            "links":[

            ]
         },
         {
            "cardId":2,
            "description":"Testcard1",
            "imageURL":"https://www.underconsideration.com/brandnew/archives/karel_de_grote_logo_detail.png",
            "themeId":1,
            "position":0,
            "links":[

            ]
         },
         {
            "cardId":3,
            "description":"Testcard2",
            "imageURL":"https://www.underconsideration.com/brandnew/archives/karel_de_grote_logo_detail.png",
            "themeId":1,
            "position":0,
            "links":[

            ]
         },
         {
            "cardId":4,
            "description":"Testcard3",
            "imageURL":"https://www.underconsideration.com/brandnew/archives/karel_de_grote_logo_detail.png",
            "themeId":1,
            "position":0,
            "links":[

            ]
         },
         {
            "cardId":5,
            "description":"Testcard4",
            "imageURL":"https://www.underconsideration.com/brandnew/archives/karel_de_grote_logo_detail.png",
            "themeId":1,
            "position":0,
            "links":[

            ]
         },
         {
            "cardId":6,
            "description":"Testcard5",
            "imageURL":"https://www.underconsideration.com/brandnew/archives/karel_de_grote_logo_detail.png",
            "themeId":1,
            "position":0,
            "links":[

            ]
         }
      ],
      "subThemes":[
         {
            "subThemeId":1,
            "subThemeName":"SubThemeKdG",
            "description":"KdG Subtheme description",
            "iconURL":"http://www.droscher.com/gallery3/var/albums/travel/Anniversary2011/Antwerp-0021.jpg?m=1310969231.jpeg",
            "organisation":{
               "organisationId":1,
               "organisationName":"Karel De Grote",
               "address":"Groenplaats 5 2000 Antwerpen",
               "logoURL":"https://pbs.twimg.com/profile_images/664027982718177280/YUs5qbQb.png",
               "countUsers":0,
               "organiser":false,
               "links":[

               ]
            },
            "cards":[

            ],
            "themeId":null,
            "links":[

            ]
         }
      ],
      "countSubthemes":0,
      "links":[

      ]
   },
   "subTheme":null,
   "playtime":0,
   "links":[

   ]
}
 */
public class SessionDTO {
    private int sessionId;
    private String sessionName;
    private SessionMode mode;
    private SessionType type;
    private SessionState state;
    private int minCards;
    private int maxCards;
    private int size;
    private String startTime;
    private String endTime;
    private boolean userAddCards;
    private int themeId;
    private int subThemeId;
    private boolean chosenCards;
    private List<Card> cards;
    private List<User> users;
    private Theme theme;
    private SubTheme subTheme;
    private int playtime;

    public SessionDTO() {
    }

    public int getSubThemeId() {
        return subThemeId;
    }

    public void setSubThemeId(int subThemeId) {
        this.subThemeId = subThemeId;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public SubTheme getSubTheme() {
        return subTheme;
    }

    public void setSubTheme(SubTheme subTheme) {
        this.subTheme = subTheme;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public SessionMode getMode() {
        return mode;
    }

    public void setMode(SessionMode mode) {
        this.mode = mode;
    }

    public SessionType getType() {
        return type;
    }

    public void setType(SessionType type) {
        this.type = type;
    }

    public SessionState getState() {
        return state;
    }

    public void setState(SessionState state) {
        this.state = state;
    }

    public int getMinCards() {
        return minCards;
    }

    public void setMinCards(int minCards) {
        this.minCards = minCards;
    }

    public int getMaxCards() {
        return maxCards;
    }

    public void setMaxCards(int maxCards) {
        this.maxCards = maxCards;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isUserAddCards() {
        return userAddCards;
    }

    public void setUserAddCards(boolean userAddCards) {
        this.userAddCards = userAddCards;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public boolean isChosenCards() {
        return chosenCards;
    }

    public void setChosenCards(boolean chosenCards) {
        this.chosenCards = chosenCards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
