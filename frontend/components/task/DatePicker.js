import React from "react";
import { Datepicker } from "@ui-kitten/components";
import { FormControl } from "native-base";

const DatePicker = () => {
  const [date, setDate] = React.useState();

  return (
    <FormControl>
      <FormControl.Label>Deadline</FormControl.Label>
      <Datepicker
        date={date}
        onSelect={(nextDate) => setDate(nextDate)}
        placeholder={"No deadline set yet."}
        controlStyle={{backgroundColor:"transperant", borderColor:"#d4d4d8"}}
      />
    </FormControl>
  );
};

export default DatePicker;