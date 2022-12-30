import React, { useContext, useEffect, useState, useRef, useCallback} from "react";
import TimeSlider from "./TimeSlider";
import { Ionicons, MaterialIcons, AntDesign } from '@expo/vector-icons'; 
import  {StyleSheet, CircleTimer} from "react-native";
import { CountdownCircleTimer } from 'react-native-countdown-circle-timer'
import { MenuProvider, Menu} from 'react-native-popup-menu';
import { TouchableOpacity } from "react-native-web";
import { Audio } from 'expo';


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
//    const array = [2, 1]
   const [array, setArray] = useState([25, 5])
   const [index, setIndex] = useState(0)
   const [duration, setDuration] = useState(array[0])
   const [isPaused, setIsPaused] = useState(true)
   const [isReplayed, setIsReplayed] = useState(false)
   const [showSettings, setShowSettings] = useState(false)
   const [timeRemaining, setTimeRemaining] = useState(array[0])
   const [backgroundMusic, setBackgroundMusic] = useState()
   const timerRef = useRef()


   useEffect(() => {
   }, [isPaused, timeRemaining]);


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
        setTimeRemaining(array[index])
        console.log("..."+timeRemaining)
        setIsPaused(true);
   }

   const updateTimer = (time) => setTimeRemaining(time) 
 
   const toggleShowSettings = () => setShowSettings(!showSettings)

   const setArrayElement = (index, value) => {
        setArray(items => {
            return items.map((item, j) => {
                return j === index ? value : item
            })
        })
    }

    const playAudio = async () => {
        setBackgroundMusic(new Audio.Sound());
        await backgroundMusic.loadAsync(require("../../assets/music/alarm.mp3"));
        await backgroundMusic.setIsLoopingAsync(true);
        await backgroundMusic.playAsync();
    }

   return (
        <View style={styles.centered}>
            <CountdownCircleTimer size={220}
                key={index}
                isPlaying={!isPaused}
                initialRemainingTime={timeRemaining}
                duration={array[index]}
                colors={['#004777', '#C70000']}
                colorsTime={[array[index], 59]}
                onUpdate={(v) => {updateTimer(v); console.log("update " + v);}}
                onComplete={() => {
                    setIndex((index + 1) % array.length)
                    setTimeRemaining(array[(index + 1) % array.length])
                    // setDuration(array[(index + 1) % array.length])
                    pauseHandler()
                    playAudio()
                    return {shouldRepeat: true, delay: 1.5}
                }}
            >
                {({ remainingTime }) => <Text style={{fontFamily:'Impact', fontWeight:'bold', fontSize:28}}>{Math.floor(remainingTime / 60) < 10 ? '0' + Math.floor(remainingTime / 60) : Math.floor(remainingTime / 60)}: 
                {remainingTime % 60 < 10 ? '0' + (remainingTime % 60) : (remainingTime % 60)}</Text>}
            </CountdownCircleTimer>
            <Box style={{flexDirection:"row", paddingTop:25}}>
                <TouchableOpacity>
                    <MaterialIcons name="replay" size={30} color="black" onPress={() => {replayHandler()}}/>
                </TouchableOpacity> 
                <TouchableOpacity>
                    {isPaused && <MaterialIcons name="play-arrow" size={30} color="black" onPress={() =>  {playHandler(); setShowSettings(false);
                    console.log("-----")}}/>}
                </TouchableOpacity>
                <TouchableOpacity>
                    {!isPaused && <MaterialIcons name="pause" size={30} color="black" onPress={() => {pauseHandler()}}/>}
                </TouchableOpacity> 
            </Box>
                <TouchableOpacity>
                    {isReplayed && <MaterialIcons name="settings" on size={30} color="black" onPress={() => toggleShowSettings()}/>}
                </TouchableOpacity>
                {showSettings && <View width={500}>
                    <TimeSlider inputText={"Study Duration"} defaultValue={array[0]} onChange={v => {setArrayElement(0, v); console.log(array)
                    }}/>
                    <TimeSlider inputText={"Break Duration"} defaultValue={array[1]} onChange={v => {setArrayElement(1, v); console.log(array)
                    }}/>
            </View>}           
        </View>
    );
};

const styles = StyleSheet.create({
  centered: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  timer: {
    paddingBottom:20
  }
});

