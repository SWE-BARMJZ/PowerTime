import * as React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { StatusBar } from 'expo-status-bar';
import { View, StyleSheet } from 'react-native';


import { Home } from './Components/Home';
import { Login } from './Components/Login';
import { SignUp } from './Components/SignUp';
import { PasswordRecovery } from './Components/PasswordRecovery';



const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <View style={{backgroundColor:'#dffaef',flex:1}}>
      <View style={Styles.container}>
        <StatusBar style="dark" />
        <NavigationContainer>
          <Stack.Navigator screenOptions={{headerShown: false}}>
            <Stack.Screen name="Sign Up" component={SignUp} />
            <Stack.Screen name="Login" component={Login} />
            <Stack.Screen name="Password Recovery" component={PasswordRecovery} />
            <Stack.Screen name="Home" component={Home}/>
          </Stack.Navigator> 
        </NavigationContainer>
      </View>
    </View>
    
  );
}

const Styles = StyleSheet.create({
  container: {
      marginTop: 45,
      flex: 1,
      backgroundColor:'#dffaef'
  }
});
