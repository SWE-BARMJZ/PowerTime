import React, { useContext, useEffect, useState } from "react";
import TimeSlider from "./TimeSlider";
import { Ionicons, MaterialIcons, AntDesign } from '@expo/vector-icons'; 
import  {StyleSheet, CircleTimer} from "react-native";
import { CountdownCircleTimer } from 'react-native-countdown-circle-timer'



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
   const [startTime, setStartTime] = useState(Date.now())
   const [isPaused, setIsPaused] = useState(true)
   var timeRemaining = duration * 60 * 1000

//    const deadline = Date.now() + duration * 60 * 1000

   useEffect(() => {
        const interval = setInterval(() => getTime(), 1000);
        return () => clearInterval(interval);
   }, [isPaused]);

   const getTime = () => {
        if (!isPaused) {
            const timeElapsed = Date.now() - startTime
            console.log("time Remain " + (duration * 60 * 1000 - timeElapsed))
            timeRemaining = duration * 60 * 1000 - timeElapsed;
            console.log(timeRemaining)
            setMinutes(Math.floor(timeRemaining / (1000 * 60)));
            setSeconds(Math.floor((timeRemaining / 1000) % 60));
        }
   }

   const playHandler = () =>  {
        setStartTime(Date.now());
        setIsPaused(false);
   }

   const pauseHandler = () => {
        setIsPaused(true);
        setDuration(Math.floor(timeRemaining / (1000 * 60)))
    }

   const replayHandler = () => {
        console.log(startTime)
        timeRemaining = duration * 60 * 1000;
        setMinutes(duration)
        setSeconds(0)
        setIsPaused(true);
   }

   const adjustDuration = (v) => {
        if (isPaused) {
            setDuration(v);
            timeRemaining = duration * 60
            setMinutes(duration);
            setSeconds(0);
        }
   }


    return (
        <View>
            <CountdownCircleTimer
                isPlaying={isPaused}
                initialRemainingTime={duration}
                duration={duration * 60}
                colors={['#C70000']}
                colorsTime={[59]}
            >
                {({ remainingTime }) => <Text>{remainingTime}</Text>}
            </CountdownCircleTimer>
            {/*<AntDesign name="playcircleo" size={40} color="#40759c" />*/}
            {/*<Ionicons name="pause-circle-outline" size={40} color="#40759c" />*/}
            <MaterialIcons name="play-arrow" size={24} color="black" onPress={() =>  {playHandler()
            console.log("-----")}}/>
            <MaterialIcons name="pause" size={24} color="black" onPress={() => pauseHandler()}/>
            <MaterialIcons name="replay" size={24} color="black" onPress={() => replayHandler()}/>
            <Text>{minutes < 10 ? '0' + minutes : minutes} : {seconds < 10 ? '0' + seconds : seconds}</Text>
            <TimeSlider onChange={v => {adjustDuration(v);
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



