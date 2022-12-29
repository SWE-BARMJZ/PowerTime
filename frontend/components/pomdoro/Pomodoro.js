import React, { useContext, useEffect, useState, useRef, useCallback} from "react";
import TimeSlider from "./TimeSlider";
import { Ionicons, MaterialIcons, AntDesign } from '@expo/vector-icons'; 
import  {StyleSheet, CircleTimer} from "react-native";
import { CountdownCircleTimer } from 'react-native-countdown-circle-timer'
import { MenuProvider, Menu} from 'react-native-popup-menu';


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
  PlayIcon,
} from "native-base";

export const Pomodoro = ({ navigation }) => {
//    const [studyDuration, setStudyDuration] = useState(Math.floor(2))
//    const [breakDuration, setBreakDuration] = useState(1)
   const array = [2, 1]
   const [index, setIndex] = useState(0)
   const [duration, setDuration] = useState(array[0])
//    const [newDuration, setNewDuration] = useState(25)
   const [isPaused, setIsPaused] = useState(true)
   const [isReplayed, setIsReplayed] = useState(false)
   const [showSettings, setShowSettings] = useState(false)
//    const [showPlayIcon, setShowPlayIcon] = useState(true)
//    const [isStudying, setIsStudying] = useState(true) 
   const [timeRemaining, setTimeRemaining] = useState(duration)
   const timerRef = useRef()


   useEffect(() => {
   }, [isPaused]);


   const playHandler = () =>  {
        setIsReplayed(false);
        setIsPaused(false);
   }

   const pauseHandler = () => {
        setIsReplayed(false);
        setIsPaused(true);
    }

   const replayHandler = () => {
        setIsReplayed(true)
        // console.log("new " + studyDuration)
        // setDuration(newDuration)
        // setTimeRemaining(studyDuration * 60);
        console.log("..."+timeRemaining)
        setIsPaused(true);
   }

   const updateTimer = (time) => setTimeRemaining(time) 
 
   const toggleShowSettings = () => setShowSettings(!showSettings)

//    const togglePlayIcon = () => setShowPlayIcon(!showPlayIcon)

//    const toggleIsStudying = () => setIsStudying(!isStudying)

   
//    const handleComplete = () => {
//        setIndex((index + 1) % array.length)
//        setDuration(array[index])
//    }

//    const getDuration = () => { 
//         return isStudying ? studyDuration : breakDuration
//         // if (isStudying) {
//         //     setTimeRemaining(studyDuration)
//         //     return studyDuration
//         // }
//         // else {
//         //     setTimeRemaining(breakDuration)
//         //     return breakDuration
//         // }
//     }
    
   return (
        <View>
            <CountdownCircleTimer
                key={index}
                isPlaying={!isPaused}
                initialRemainingTime={duration}
                duration={duration}
                colors={['#004777', '#C70000']}
                colorsTime={[duration * 60, 59]}
                onUpdate={(v) => {updateTimer(v); console.log("update " + v);}}
                onComplete={() => {
                    setIndex((index + 1) % array.length)
                    setDuration(array[(index + 1) % array.length])
                    pauseHandler()
                    return {shouldRepeat: true, delay: 1.5}
                }}
            >
                {({ remainingTime }) => <Text fontFamily='Arial' fontSize='20'>{Math.floor(remainingTime / 60) < 10 ? '0' + Math.floor(remainingTime / 60) : Math.floor(remainingTime / 60)}: 
                {remainingTime % 60 < 10 ? '0' + (remainingTime % 60) : (remainingTime % 60)}</Text>}
            </CountdownCircleTimer>
            <Box style={{flexDirection:"row"}}>
                <MaterialIcons name="replay" size={24} color="black" onPress={() => {replayHandler()}}/>
                {isPaused && <MaterialIcons name="play-arrow" size={24} color="black" onPress={() =>  {playHandler()
                console.log("-----")}}/>}
                {!isPaused && <MaterialIcons name="pause" size={24} color="black" onPress={() => {pauseHandler()}}/>}
            </Box>
                {isReplayed && <MaterialIcons name="settings" size={24} color="black" onPress={() => toggleShowSettings()}/>}
                {showSettings && <View>
                    <TimeSlider inputText={"Study Duration"} defaultValue={25} onChange={v => {setStudyDuration(v);
                        console.log(studyDuration)
                    }}/>
                    <TimeSlider inputText={"Break Duration"} defaultValue={5} onChange={v => {setBreakDuration(v);
                        console.log(breakDuration)
                    }}/>
            </View>}            
        </View>
    );
};

const styles = StyleSheet.create({
    iconsStyle: {
        borderColor: '#40759c',
        width: 300
    },
});