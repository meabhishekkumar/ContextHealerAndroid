jQuery("#simulation")
  .on("click", ".s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 .click", function(event, data) {
    var jEvent, jFirer, cases;
    if(data === undefined) { data = event; }
    jEvent = jimEvent(event);
    jFirer = jEvent.getEventFirer();
    if(jFirer.is("#s-Label_12")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimNavigation",
                  "parameter": {
                    "target": "screens/be8c5242-3b77-42be-b6cc-09dc740f9bbd",
                    "transition": "fade"
                  },
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            }
          ],
          "exectype": "serial",
          "delay": 0
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    } else if(jFirer.is("#s-Image_24")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimNavigation",
                  "parameter": {
                    "target": "screens/be8c5242-3b77-42be-b6cc-09dc740f9bbd",
                    "transition": "fade"
                  },
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            }
          ],
          "exectype": "serial",
          "delay": 0
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    } else if(jFirer.is("#s-Label_6")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_6": {
                      "attributes": {
                        "background-color": "#A4A5A5",
                        "background-image": "none",
                        "font-size": "27.0pt",
                        "font-family": "materialdesignjim-Regular,Arial"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_6 .valign": {
                      "attributes": {
                        "vertical-align": "middle",
                        "text-align": "center"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_6 span": {
                      "attributes": {
                        "color": "#535358",
                        "text-align": "center",
                        "text-decoration": "none",
                        "font-family": "materialdesignjim-Regular,Arial",
                        "font-size": "27.0pt"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_6": {
                      "attributes-ie": {
                        "-pie-background": "#A4A5A5",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_7": {
                      "attributes": {
                        "background-color": "#F7F7F7",
                        "background-image": "none",
                        "font-size": "27.0pt",
                        "font-family": "materialdesignjim-Regular,Arial"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_7 .valign": {
                      "attributes": {
                        "vertical-align": "middle",
                        "text-align": "center"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_7 span": {
                      "attributes": {
                        "color": "#B5B5B5",
                        "text-align": "center",
                        "text-decoration": "none",
                        "font-family": "materialdesignjim-Regular,Arial",
                        "font-size": "27.0pt"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_7": {
                      "attributes-ie": {
                        "-pie-background": "#F7F7F7",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_8": {
                      "attributes": {
                        "background-color": "#F7F7F7",
                        "background-image": "none",
                        "font-size": "27.0pt",
                        "font-family": "materialdesignjim-Regular,Arial"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_8 .valign": {
                      "attributes": {
                        "vertical-align": "middle",
                        "text-align": "center"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_8 span": {
                      "attributes": {
                        "color": "#B5B5B5",
                        "text-align": "center",
                        "text-decoration": "none",
                        "font-family": "materialdesignjim-Regular,Arial",
                        "font-size": "27.0pt"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_8": {
                      "attributes-ie": {
                        "-pie-background": "#F7F7F7",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            }
          ],
          "exectype": "serial",
          "delay": 0
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    } else if(jFirer.is("#s-Label_7")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_6": {
                      "attributes": {
                        "background-color": "#F7F7F7",
                        "background-image": "none",
                        "font-size": "27.0pt",
                        "font-family": "materialdesignjim-Regular,Arial"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_6 .valign": {
                      "attributes": {
                        "vertical-align": "middle",
                        "text-align": "center"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_6 span": {
                      "attributes": {
                        "color": "#B5B5B5",
                        "text-align": "center",
                        "text-decoration": "none",
                        "font-family": "materialdesignjim-Regular,Arial",
                        "font-size": "27.0pt"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_6": {
                      "attributes-ie": {
                        "-pie-background": "#F7F7F7",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_7": {
                      "attributes": {
                        "background-color": "#C6C6C6",
                        "background-image": "none",
                        "font-size": "27.0pt",
                        "font-family": "materialdesignjim-Regular,Arial"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_7 .valign": {
                      "attributes": {
                        "vertical-align": "middle",
                        "text-align": "center"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_7 span": {
                      "attributes": {
                        "color": "#535358",
                        "text-align": "center",
                        "text-decoration": "none",
                        "font-family": "materialdesignjim-Regular,Arial",
                        "font-size": "27.0pt"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_7": {
                      "attributes-ie": {
                        "-pie-background": "#C6C6C6",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_8": {
                      "attributes": {
                        "background-color": "#F7F7F7",
                        "background-image": "none",
                        "font-size": "27.0pt",
                        "font-family": "materialdesignjim-Regular,Arial"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_8 .valign": {
                      "attributes": {
                        "vertical-align": "middle",
                        "text-align": "center"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_8 span": {
                      "attributes": {
                        "color": "#B5B5B5",
                        "text-align": "center",
                        "text-decoration": "none",
                        "font-family": "materialdesignjim-Regular,Arial",
                        "font-size": "27.0pt"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_8": {
                      "attributes-ie": {
                        "-pie-background": "#F7F7F7",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            }
          ],
          "exectype": "serial",
          "delay": 0
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    } else if(jFirer.is("#s-Label_8")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_6": {
                      "attributes": {
                        "background-color": "#F7F7F7",
                        "background-image": "none",
                        "font-size": "27.0pt",
                        "font-family": "materialdesignjim-Regular,Arial"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_6 .valign": {
                      "attributes": {
                        "vertical-align": "middle",
                        "text-align": "center"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_6 span": {
                      "attributes": {
                        "color": "#B5B5B5",
                        "text-align": "center",
                        "text-decoration": "none",
                        "font-family": "materialdesignjim-Regular,Arial",
                        "font-size": "27.0pt"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_6": {
                      "attributes-ie": {
                        "-pie-background": "#F7F7F7",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_7": {
                      "attributes": {
                        "background-color": "#F7F7F7",
                        "background-image": "none",
                        "font-size": "27.0pt",
                        "font-family": "materialdesignjim-Regular,Arial"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_7 .valign": {
                      "attributes": {
                        "vertical-align": "middle",
                        "text-align": "center"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_7 span": {
                      "attributes": {
                        "color": "#B5B5B5",
                        "text-align": "center",
                        "text-decoration": "none",
                        "font-family": "materialdesignjim-Regular,Arial",
                        "font-size": "27.0pt"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_7": {
                      "attributes-ie": {
                        "-pie-background": "#F7F7F7",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_8": {
                      "attributes": {
                        "background-color": "#C6C6C6",
                        "background-image": "none",
                        "font-size": "27.0pt",
                        "font-family": "materialdesignjim-Regular,Arial"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_8 .valign": {
                      "attributes": {
                        "vertical-align": "middle",
                        "text-align": "center"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_8 span": {
                      "attributes": {
                        "color": "#4C4C4C",
                        "text-align": "center",
                        "text-decoration": "none",
                        "font-family": "materialdesignjim-Regular,Arial",
                        "font-size": "27.0pt"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Label_8": {
                      "attributes-ie": {
                        "-pie-background": "#C6C6C6",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            }
          ],
          "exectype": "serial",
          "delay": 0
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    }
  })
  .on("click", ".s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 .toggle", function(event, data) {
    var jEvent, jFirer, cases;
    if(data === undefined) { data = event; }
    jEvent = jimEvent(event);
    jFirer = jEvent.getEventFirer();
    if(jFirer.is("#s-checkbox-on-light_4")) {
      if(jFirer.data("jimHasToggle")) {
        jFirer.removeData("jimHasToggle");
        jEvent.undoCases(jFirer);
      } else {
        jFirer.data("jimHasToggle", true);
        event.backupState = true;
        event.target = jFirer;
        cases = [
          {
            "blocks": [
              {
                "actions": [
                  {
                    "action": "jimSetValue",
                    "parameter": {
                      "target": "#s-checkbox-on-light_4",
                      "value": ""
                    },
                    "exectype": "serial",
                    "delay": 0
                  },
                  {
                    "action": "jimChangeStyle",
                    "parameter": [ {
                      "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-checkbox-on-light_4": {
                        "attributes": {
                          "font-size": "20.0pt",
                          "font-family": "materialdesignjim-Regular,Arial"
                        }
                      }
                    },{
                      "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-checkbox-on-light_4 .valign": {
                        "attributes": {
                          "vertical-align": "middle",
                          "text-align": "left"
                        }
                      }
                    },{
                      "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-checkbox-on-light_4 span": {
                        "attributes": {
                          "color": "#333333",
                          "text-align": "left",
                          "text-decoration": "none",
                          "font-family": "materialdesignjim-Regular,Arial",
                          "font-size": "20.0pt"
                        }
                      }
                    } ],
                    "exectype": "serial",
                    "delay": 0
                  }
                ]
              }
            ],
            "exectype": "serial",
            "delay": 0
          }
        ];
        jEvent.launchCases(cases);
      }
    } else if(jFirer.is("#s-checkbox-on-light_6")) {
      if(jFirer.data("jimHasToggle")) {
        jFirer.removeData("jimHasToggle");
        jEvent.undoCases(jFirer);
      } else {
        jFirer.data("jimHasToggle", true);
        event.backupState = true;
        event.target = jFirer;
        cases = [
          {
            "blocks": [
              {
                "actions": [
                  {
                    "action": "jimSetValue",
                    "parameter": {
                      "target": "#s-checkbox-on-light_6",
                      "value": ""
                    },
                    "exectype": "serial",
                    "delay": 0
                  },
                  {
                    "action": "jimChangeStyle",
                    "parameter": [ {
                      "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-checkbox-on-light_6": {
                        "attributes": {
                          "font-size": "20.0pt",
                          "font-family": "materialdesignjim-Regular,Arial"
                        }
                      }
                    },{
                      "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-checkbox-on-light_6 .valign": {
                        "attributes": {
                          "vertical-align": "middle",
                          "text-align": "left"
                        }
                      }
                    },{
                      "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-checkbox-on-light_6 span": {
                        "attributes": {
                          "color": "#333333",
                          "text-align": "left",
                          "text-decoration": "none",
                          "font-family": "materialdesignjim-Regular,Arial",
                          "font-size": "20.0pt"
                        }
                      }
                    } ],
                    "exectype": "serial",
                    "delay": 0
                  }
                ]
              }
            ],
            "exectype": "serial",
            "delay": 0
          }
        ];
        jEvent.launchCases(cases);
      }
    } else if(jFirer.is("#s-checkbox-on-light_7")) {
      if(jFirer.data("jimHasToggle")) {
        jFirer.removeData("jimHasToggle");
        jEvent.undoCases(jFirer);
      } else {
        jFirer.data("jimHasToggle", true);
        event.backupState = true;
        event.target = jFirer;
        cases = [
          {
            "blocks": [
              {
                "actions": [
                  {
                    "action": "jimSetValue",
                    "parameter": {
                      "target": "#s-checkbox-on-light_7",
                      "value": ""
                    },
                    "exectype": "serial",
                    "delay": 0
                  },
                  {
                    "action": "jimChangeStyle",
                    "parameter": [ {
                      "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-checkbox-on-light_7": {
                        "attributes": {
                          "font-size": "20.0pt",
                          "font-family": "materialdesignjim-Regular,Arial"
                        }
                      }
                    },{
                      "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-checkbox-on-light_7 .valign": {
                        "attributes": {
                          "vertical-align": "middle",
                          "text-align": "left"
                        }
                      }
                    },{
                      "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-checkbox-on-light_7 span": {
                        "attributes": {
                          "color": "#333333",
                          "text-align": "left",
                          "text-decoration": "none",
                          "font-family": "materialdesignjim-Regular,Arial",
                          "font-size": "20.0pt"
                        }
                      }
                    } ],
                    "exectype": "serial",
                    "delay": 0
                  }
                ]
              }
            ],
            "exectype": "serial",
            "delay": 0
          }
        ];
        jEvent.launchCases(cases);
      }
    } else if(jFirer.is("#s-checkbox-on-light_8")) {
      if(jFirer.data("jimHasToggle")) {
        jFirer.removeData("jimHasToggle");
        jEvent.undoCases(jFirer);
      } else {
        jFirer.data("jimHasToggle", true);
        event.backupState = true;
        event.target = jFirer;
        cases = [
          {
            "blocks": [
              {
                "actions": [
                  {
                    "action": "jimSetValue",
                    "parameter": {
                      "target": "#s-checkbox-on-light_8",
                      "value": ""
                    },
                    "exectype": "serial",
                    "delay": 0
                  },
                  {
                    "action": "jimChangeStyle",
                    "parameter": [ {
                      "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-checkbox-on-light_8": {
                        "attributes": {
                          "font-size": "20.0pt",
                          "font-family": "materialdesignjim-Regular,Arial"
                        }
                      }
                    },{
                      "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-checkbox-on-light_8 .valign": {
                        "attributes": {
                          "vertical-align": "middle",
                          "text-align": "left"
                        }
                      }
                    },{
                      "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-checkbox-on-light_8 span": {
                        "attributes": {
                          "color": "#333333",
                          "text-align": "left",
                          "text-decoration": "none",
                          "font-family": "materialdesignjim-Regular,Arial",
                          "font-size": "20.0pt"
                        }
                      }
                    } ],
                    "exectype": "serial",
                    "delay": 0
                  }
                ]
              }
            ],
            "exectype": "serial",
            "delay": 0
          }
        ];
        jEvent.launchCases(cases);
      }
    } else if(jFirer.is("#s-checkbox-on-light_9")) {
      if(jFirer.data("jimHasToggle")) {
        jFirer.removeData("jimHasToggle");
        jEvent.undoCases(jFirer);
      } else {
        jFirer.data("jimHasToggle", true);
        event.backupState = true;
        event.target = jFirer;
        cases = [
          {
            "blocks": [
              {
                "actions": [
                  {
                    "action": "jimSetValue",
                    "parameter": {
                      "target": "#s-checkbox-on-light_9",
                      "value": ""
                    },
                    "exectype": "serial",
                    "delay": 0
                  },
                  {
                    "action": "jimChangeStyle",
                    "parameter": [ {
                      "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-checkbox-on-light_9": {
                        "attributes": {
                          "font-size": "20.0pt",
                          "font-family": "materialdesignjim-Regular,Arial"
                        }
                      }
                    },{
                      "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-checkbox-on-light_9 .valign": {
                        "attributes": {
                          "vertical-align": "middle",
                          "text-align": "left"
                        }
                      }
                    },{
                      "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-checkbox-on-light_9 span": {
                        "attributes": {
                          "color": "#333333",
                          "text-align": "left",
                          "text-decoration": "none",
                          "font-family": "materialdesignjim-Regular,Arial",
                          "font-size": "20.0pt"
                        }
                      }
                    } ],
                    "exectype": "serial",
                    "delay": 0
                  }
                ]
              }
            ],
            "exectype": "serial",
            "delay": 0
          }
        ];
        jEvent.launchCases(cases);
      }
    } else if(jFirer.is("#s-checkbox-on-light_11")) {
      if(jFirer.data("jimHasToggle")) {
        jFirer.removeData("jimHasToggle");
        jEvent.undoCases(jFirer);
      } else {
        jFirer.data("jimHasToggle", true);
        event.backupState = true;
        event.target = jFirer;
        cases = [
          {
            "blocks": [
              {
                "actions": [
                  {
                    "action": "jimSetValue",
                    "parameter": {
                      "target": "#s-checkbox-on-light_11",
                      "value": ""
                    },
                    "exectype": "serial",
                    "delay": 0
                  },
                  {
                    "action": "jimChangeStyle",
                    "parameter": [ {
                      "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-checkbox-on-light_11": {
                        "attributes": {
                          "font-size": "20.0pt",
                          "font-family": "materialdesignjim-Regular,Arial"
                        }
                      }
                    },{
                      "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-checkbox-on-light_11 .valign": {
                        "attributes": {
                          "vertical-align": "middle",
                          "text-align": "left"
                        }
                      }
                    },{
                      "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-checkbox-on-light_11 span": {
                        "attributes": {
                          "color": "#08AE9E",
                          "text-align": "left",
                          "text-decoration": "none",
                          "font-family": "materialdesignjim-Regular,Arial",
                          "font-size": "20.0pt"
                        }
                      }
                    } ],
                    "exectype": "serial",
                    "delay": 0
                  }
                ]
              }
            ],
            "exectype": "serial",
            "delay": 0
          }
        ];
        jEvent.launchCases(cases);
      }
    } else if(jFirer.is("#s-checkbox-on-light_12")) {
      if(jFirer.data("jimHasToggle")) {
        jFirer.removeData("jimHasToggle");
        jEvent.undoCases(jFirer);
      } else {
        jFirer.data("jimHasToggle", true);
        event.backupState = true;
        event.target = jFirer;
        cases = [
          {
            "blocks": [
              {
                "actions": [
                  {
                    "action": "jimSetValue",
                    "parameter": {
                      "target": "#s-checkbox-on-light_12",
                      "value": ""
                    },
                    "exectype": "serial",
                    "delay": 0
                  },
                  {
                    "action": "jimChangeStyle",
                    "parameter": [ {
                      "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-checkbox-on-light_12": {
                        "attributes": {
                          "font-size": "20.0pt",
                          "font-family": "materialdesignjim-Regular,Arial"
                        }
                      }
                    },{
                      "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-checkbox-on-light_12 .valign": {
                        "attributes": {
                          "vertical-align": "middle",
                          "text-align": "left"
                        }
                      }
                    },{
                      "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-checkbox-on-light_12 span": {
                        "attributes": {
                          "color": "#08AE9E",
                          "text-align": "left",
                          "text-decoration": "none",
                          "font-family": "materialdesignjim-Regular,Arial",
                          "font-size": "20.0pt"
                        }
                      }
                    } ],
                    "exectype": "serial",
                    "delay": 0
                  }
                ]
              }
            ],
            "exectype": "serial",
            "delay": 0
          }
        ];
        jEvent.launchCases(cases);
      }
    }
  })
  .on("focusin", ".s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 .focusin", function(event, data) {
    var jEvent, jFirer, cases;
    if(data === undefined) { data = event; }
    jEvent = jimEvent(event);
    jFirer = jEvent.getEventFirer();
    if(jFirer.is("#s-Input_2")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimShow",
                  "parameter": {
                    "target": "#s-Line_4",
                    "effect": {
                      "type": "slide",
                      "easing": "linear",
                      "duration": 200,
                      "direction": "left"
                    }
                  },
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Input_2 .valign": {
                      "attributes": {
                        "vertical-align": "middle",
                        "line-height": "11.0pt"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Input_2 input": {
                      "attributes": {
                        "color": "#434343",
                        "text-align": "left",
                        "text-decoration": "none",
                        "font-family": "Roboto-Light,Arial",
                        "font-size": "11.0pt"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            }
          ],
          "exectype": "serial",
          "delay": 0
        },
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "property",
                  "target": "#s-Input_2",
                  "property": "jimGetValue"
                },"Type something" ]
              },
              "actions": [
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "target": "#s-Input_2",
                    "value": ""
                  },
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            }
          ],
          "exectype": "serial",
          "delay": 0
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    } else if(jFirer.is("#s-Input_3")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimShow",
                  "parameter": {
                    "target": "#s-Line_6",
                    "effect": {
                      "type": "slide",
                      "easing": "linear",
                      "duration": 200,
                      "direction": "left"
                    }
                  },
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Input_3 .valign": {
                      "attributes": {
                        "vertical-align": "middle",
                        "line-height": "11.0pt"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Input_3 input": {
                      "attributes": {
                        "color": "#434343",
                        "text-align": "left",
                        "text-decoration": "none",
                        "font-family": "Roboto-Light,Arial",
                        "font-size": "11.0pt"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            }
          ],
          "exectype": "serial",
          "delay": 0
        },
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "property",
                  "target": "#s-Input_3",
                  "property": "jimGetValue"
                },"Type something" ]
              },
              "actions": [
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "target": "#s-Input_3",
                    "value": ""
                  },
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            }
          ],
          "exectype": "serial",
          "delay": 0
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    } else if(jFirer.is("#s-Input_4")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimShow",
                  "parameter": {
                    "target": "#s-Line_8",
                    "effect": {
                      "type": "slide",
                      "easing": "linear",
                      "duration": 200,
                      "direction": "left"
                    }
                  },
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Input_4 .valign": {
                      "attributes": {
                        "vertical-align": "middle",
                        "line-height": "11.0pt"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Input_4 input": {
                      "attributes": {
                        "color": "#434343",
                        "text-align": "left",
                        "text-decoration": "none",
                        "font-family": "Roboto-Light,Arial",
                        "font-size": "11.0pt"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            }
          ],
          "exectype": "serial",
          "delay": 0
        },
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "property",
                  "target": "#s-Input_4",
                  "property": "jimGetValue"
                },"Type something" ]
              },
              "actions": [
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "target": "#s-Input_4",
                    "value": ""
                  },
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            }
          ],
          "exectype": "serial",
          "delay": 0
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    }
  })
  .on("focusout", ".s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 .focusout", function(event, data) {
    var jEvent, jFirer, cases;
    if(data === undefined) { data = event; }
    jEvent = jimEvent(event);
    jFirer = jEvent.getEventFirer();
    if(jFirer.is("#s-Input_2")) {
      cases = [
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "property",
                  "target": "#s-Input_2",
                  "property": "jimGetValue"
                },"" ]
              },
              "actions": [
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "target": "#s-Input_2",
                    "value": "Type something"
                  },
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            }
          ],
          "exectype": "serial",
          "delay": 0
        },
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimHide",
                  "parameter": {
                    "target": "#s-Line_4"
                  },
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Input_2 .valign": {
                      "attributes": {
                        "vertical-align": "middle",
                        "line-height": "11.0pt"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Input_2 input": {
                      "attributes": {
                        "color": "#808080",
                        "text-align": "left",
                        "text-decoration": "none",
                        "font-family": "Roboto-Light,Arial",
                        "font-size": "11.0pt"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            }
          ],
          "exectype": "serial",
          "delay": 0
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    } else if(jFirer.is("#s-Input_3")) {
      cases = [
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "property",
                  "target": "#s-Input_3",
                  "property": "jimGetValue"
                },"" ]
              },
              "actions": [
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "target": "#s-Input_3",
                    "value": "Type something"
                  },
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            }
          ],
          "exectype": "serial",
          "delay": 0
        },
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimHide",
                  "parameter": {
                    "target": "#s-Line_6"
                  },
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Input_3 .valign": {
                      "attributes": {
                        "vertical-align": "middle",
                        "line-height": "11.0pt"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Input_3 input": {
                      "attributes": {
                        "color": "#808080",
                        "text-align": "left",
                        "text-decoration": "none",
                        "font-family": "Roboto-Light,Arial",
                        "font-size": "11.0pt"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            }
          ],
          "exectype": "serial",
          "delay": 0
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    } else if(jFirer.is("#s-Input_4")) {
      cases = [
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "property",
                  "target": "#s-Input_4",
                  "property": "jimGetValue"
                },"" ]
              },
              "actions": [
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "target": "#s-Input_4",
                    "value": "Type something"
                  },
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            }
          ],
          "exectype": "serial",
          "delay": 0
        },
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimHide",
                  "parameter": {
                    "target": "#s-Line_8"
                  },
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Input_4 .valign": {
                      "attributes": {
                        "vertical-align": "middle",
                        "line-height": "11.0pt"
                      }
                    }
                  },{
                    "#s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 #s-Input_4 input": {
                      "attributes": {
                        "color": "#808080",
                        "text-align": "left",
                        "text-decoration": "none",
                        "font-family": "Roboto-Light,Arial",
                        "font-size": "11.0pt"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            }
          ],
          "exectype": "serial",
          "delay": 0
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    }
  });