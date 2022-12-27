import React, { useState } from 'react';
import {
  Button,
  Text,
  Image,
  Heading,
  HStack,
  Link,
  Box,
  VStack,
  Hidden,
  useToast,
  View,
  Slider
} from "native-base";


const TimeSlider = ({onChange}) => {
    const [value, setValue] = useState(25)
    return (
            <Box alignItems="center" w="100%">
            <Slider maxW="300" defaultValue={25} minValue={0} maxValue={60} accessibilityLabel="hello world" step={1}
            onChange={v => setValue(v)}
            onChangeEnd={onChange}>
                <Slider.Track>
                <Slider.FilledTrack />
                </Slider.Track>
                <Slider.Thumb />
            </Slider>
            <Text>{value} min</Text>
            </Box>);
};

export default TimeSlider;