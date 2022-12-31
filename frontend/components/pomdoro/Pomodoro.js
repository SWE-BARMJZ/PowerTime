import React, { useContext, useEffect, useState, useRef, useCallback} from "react";
import TimeSlider from "./TimeSlider";
import { Ionicons, MaterialIcons, AntDesign } from '@expo/vector-icons'; 
import  {StyleSheet, CircleTimer} from "react-native";
import { CountdownCircleTimer } from 'react-native-countdown-circle-timer'
import { MenuProvider, Menu} from 'react-native-popup-menu';
import { TouchableOpacity } from "react-native-web";
import * as api from "../../api/pomodoro.api";
import AuthContext from "../../store/auth-context";
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
//    const [duration, setDuration] = useState(array[0])

   const auth = useContext(AuthContext)
   const [array, setArray] = useState([3, 1])
   const [index, setIndex] = useState(0)
   const [isPaused, setIsPaused] = useState(true)
   const [isReplayed, setIsReplayed] = useState(false)
   const [showSettings, setShowSettings] = useState(false)
   const [timeRemaining, setTimeRemaining] = useState(array[0] * 60)
   const [isLoaded, setIsLoaded] = useState(false)
   const [backgroundMusic, setBackgroundMusic] = useState()
   const timerRef = useRef()


   useEffect(() => {
        const getPomodoro = async () => {
            const pomodoro = await api.getPomodoro(auth.token)
            console.log(pomodoro)
            if ('studying' in pomodoro) {
                setArray([pomodoro.studyTime, pomodoro.breakTime])
                setIndex(pomodoro.studying ? 0 : 1);
                setIsPaused(pomodoro.paused)
                setTimeRemaining(pomodoro.remainingTimeInSecs);
                console.log(pomodoro.remainingTimeInSecs)
            }
            else {
                setArray([pomodoro.studyTime, pomodoro.breakTime])
                setTimeRemaining(pomodoro.studyTime * 60)
            }
            setIsLoaded(true)
        } 
        getPomodoro()
   }, []);


   const playHandler = async () =>  {
        try {
            console.log(timeRemaining)
            console.log(array[index])
            if (timeRemaining == array[index] * 60 && index == 0) {
                await api.startStudy(auth.token)
            }
            else 
                await api.resume(auth.token)
        }
        catch(error){
        }
        setIsReplayed(false);
        setIsPaused(false);
   }

   const pauseHandler = async () => {
        try {
            await api.pause(auth.token, timeRemaining)
        }
        catch(error) {

        }
        setIsReplayed(false);
        console.log("..."+timeRemaining)
        setIsPaused(true);
    }

   const replayHandler = async () => {
        console.log('replay handler called')
        setIsPaused(true);
        try {
            if (!isReplayed) {
                await api.resetPomodoro(auth.token)
                console.log('reset pomodoro')
            }
            setIsReplayed(true)
            await api.getPomodoro()
            await api.setPomodoro(auth.token, array[0], array[1])
            console.log('set pomodoro')
        }
        catch(error) {
            console.log(error.message)
            console.log('error replaying')
        }
        setTimeRemaining(array[0])
        console.log("..."+timeRemaining)
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
        // setBackgroundMusic(new Audio.Sound());
        // await backgroundMusic.loadAsync(require("../../assets/music/alarm.mp3"));
        // await backgroundMusic.setIsLoopingAsync(true);
        // await backgroundMusic.playAsync();
    }

   return (
        !isLoaded ?  <Text>Loading....</Text> : 
        <View style={styles.centered}>
            <CountdownCircleTimer size={220}
                key={index}
                isPlaying={!isPaused}
                initialRemainingTime={timeRemaining}
                duration={array[index] * 60}
                colors={['#004777', '#C70000']}
                colorsTime={[array[index], 59]}
                onUpdate={(v) => {updateTimer(v); console.log("update " + v);}}
                onComplete={async () => {
                    try {
                        if (index == 0) 
                            await api.endStudy(auth.token)
                        else
                            await api.resetPomodoro(auth.token)
                    }
                    catch(error) {

                    }
                    setTimeRemaining(array[(index + 1) % array.length] * 60)
                    setIndex((index + 1) % array.length)
                    console.log('time remaining on complete ' + timeRemaining)
                    setIsPaused(true)
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