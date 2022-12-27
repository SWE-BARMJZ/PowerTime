import React, { useContext, useEffect, useState } from "react";
import TimeSlider from "./TimeSlider";
import { Ionicons, MaterialIcons, AntDesign } from '@expo/vector-icons'; 
import  {StyleSheet} from "react-native";


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
  Slider,
} from "native-base";

export const Pomodoro = ({ navigation }) => {
   const [duration, setDuration] = useState(Math.floor(25))
   const [minutes, setMinutes] = useState(Math.floor(duration))
   const [seconds, setSeconds] = useState(Math.floor(0))

   const deadline = Date.now() + duration * 60 * 1000

   useEffect(() => {
        const interval = setInterval(() => getTime(), 1000);
        return () => clearInterval(interval);
   }, []);

   const getTime = () => {
       const time = deadline - Date.now();
       setMinutes(Math.floor(time / (1000 * 60)));
       setSeconds(Math.floor((time / 1000) % 60));
   }


    return (
        <View>
            {/*<AntDesign name="playcircleo" size={40} color="#40759c" />*/}
            {/*<Ionicons name="pause-circle-outline" size={40} color="#40759c" />*/}
            <MaterialIcons name="play-arrow" size={24} color="black"/>
            <MaterialIcons name="pause" size={24} color="black"/>
            <MaterialIcons name="replay" size={24} color="black" />
            <Text>{minutes < 9 ? '0' + minutes : minutes} : {seconds < 9 ? '0' + seconds : seconds}</Text>
            <TimeSlider onChange={v => {setDuration(v);
                console.log(duration)
            }}/>
        </View>
    );
};

const styles = StyleSheet.create({
    iconsStyle: {
        borderColor: '#40759c',
        width: 300
    },
});



