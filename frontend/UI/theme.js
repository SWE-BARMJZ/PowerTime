import { extendTheme } from "native-base";

const theme = extendTheme({
  colors: {
    primary: {
      main: "#0068F9",
      bg: "#dffaef"
    },
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
