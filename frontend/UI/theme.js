import { extendTheme } from "native-base";

const theme = extendTheme({

  breakpoints: {
    base: 0,
    sm: 480,
    md: 768,
    lg: 992,
    xl: 1280,
  },

  fonts: {
    heading: "Montserrat",
    body: "Montserrat",
    mono: "Montserrat",
  },

  colors: {
    primary: {
      main: "#0c5ac7",
      bg: "#F2F2F2",
      darkerBg: "#E1E1E1",
      evenDarkerBg: "#D6D6D6",
      accent: "#031264",
      darkBlue: "#2C406E",
      accentDark: "#312e81",
      loginBg: "#DFFAEF",
      
    },
    secondary: "#5BBA59",
  },
  components: {
    Heading: {
      baseStyle: {_text: {fontFamily: "Montserrat"}},
      defaultProps: {
        fontFamily: "Montserrat"
      },
    },
    Text: {
      baseStyle: {_text: {fontFamily: "Montserrat"}},
      defaultProps: {
        fontFamily: "Montserrat"
      },
    },
    Link: {
      baseStyle: {
        _web: {
          cursor: "pointer",
        },
      },
    },
    Button: {
      baseStyle: { _text: { fontFamily: "Montserrat" } },
      defaultProps: {
        bgColor: "primary.main",
        fontFamily: "Montserrat",
      },
    },
    Input: {
      baseStyle: {
        _hover: {
          borderColor: "primary.main",
        },
        _focus: {
          bgColor: "white",
          borderColor: "primary.main",
          _hover: {
            borderColor: "primary.main",
          },
        },
        _text: { fontFamily: "Montserrat" }
      },
      defaultProps: {
        size: "lg",
        fontFamily: "Montserrat",
      },
    },
    FormControlLabel: {
      baseStyle: {
        _text: { color: "black" },
      },
    },
    Checkbox: {
      defaultProps: {
        _checked: {
          bgColor: "primary.accent",
          borderColor: "primary.accent",
          _hover: {
            borderColor: "primary.accentDark",
          },
        },
        _hover: {
          borderColor: "primary.accentDark",
        },
        _focus: {
          borderColor: "primary.accentDark",

        }
      },
    },
    Switch: {
      defaultProps: {
        onTrackColor: "primary.accent",
        _hover: {
          onTrackColor: "primary.accentDark",
        },
      },
    },
  },
});

export { theme };
