import { extendTheme } from "native-base";

const theme = extendTheme({
  breakpoints: {
    base: 0,
    sm: 480,
    md: 768,
    lg: 992,
    xl: 1280,
  },
  colors: {
    primary: {
      main: "#0068F9",
      bg: "#dffaef"
    },
    secondary: "#5BBA59",
  },
  components: {
    Link: {
      baseStyle: {
        _web: {
          cursor: "pointer",
        },
      },
    },
    Button: {
      baseStyle: { _text: { fontWeight: "bold" } },
      defaultProps: {
        bgColor: "primary.main",
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
      },
      defaultProps: {
        size: "lg",
      },
    },
    FormControlLabel: {
      baseStyle: {
        _text: { color: 'black' },
      },
    },
  },
});


export { theme };
