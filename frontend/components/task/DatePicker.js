import React from "react";
import { Datepicker } from "@ui-kitten/components";
import { Box, HStack, Button } from "native-base";

const DatePicker = ({ value, setValue }) => {
  return (
    <HStack space={4}>
      <Box flex={1}>
        <Datepicker
          date={value}
          onSelect={(v) => setValue(v)}
          placeholder={"No deadline set yet."}
          controlStyle={{
            borderColor: "#d4d4d8",
          }}
        />
      </Box>
      <Button
        isDisabled={value === null}
        onPress={() => setValue(null)}
        bgColor="gray.600"
      >
        Clear
      </Button>
    </HStack>
  );
};

export default DatePicker;
