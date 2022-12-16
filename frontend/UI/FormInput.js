import React, { useState } from "react";
import { FormControl, WarningOutlineIcon, Input } from "native-base";

const FormInput = ({
  label,
  placeholder,
  inputType,
  helperText,
  error,
  inputValue,
  validationFn = (text) => true ,
  onChange: outerOnChange,
}) => {
  const [isValid, setIsValid] = useState(true);

  const onChange = (text) => {
    setIsValid(validationFn(text));
    if (outerOnChange)
      outerOnChange(text);
  };

  return (
    <FormControl isInvalid={!isValid}>
      <FormControl.Label>{label}</FormControl.Label>
      <Input
        placeholder={placeholder}
        value={inputValue}
        onChangeText={onChange}
        type={inputType}
      />

      {helperText && (
        <FormControl.HelperText>{helperText}</FormControl.HelperText>
      )}

      {error && (
        <FormControl.ErrorMessage leftIcon={<WarningOutlineIcon size="xs" />}>
          {error}
        </FormControl.ErrorMessage>
      )}
    </FormControl>
  );
};

export default FormInput;
