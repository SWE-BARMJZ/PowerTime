import * as React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { StatusBar } from 'expo-status-bar';
import { View } from 'react-native';

import { Home } from './Components/Home';
import { Login } from './Components/Login';
import { SignUp } from './Components/SignUp';


const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <View>
      <StatusBar style="dark" />
      <NavigationContainer>
        <Stack.Navigator>
          <Stack.Screen name="Home" component={Home}/>
          <Stack.Screen name="Login" component={Login} />
          <Stack.Screen name="SignUp" component={SignUp} />
        </Stack.Navigator> 
      </NavigationContainer>
    </View>
  );
}



