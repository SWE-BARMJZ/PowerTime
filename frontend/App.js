import * as React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { Home } from './Components/Home'
import { Login } from './Components/Login'

const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator> 
        <Stack.Screen 
          name="Home" 
          component={Home} 
          options={{ title: 'Home' }} 
        /> 
        <Stack.Screen 
          name="Login" 
          component={Login} 
        /> 
      </Stack.Navigator> 
    </NavigationContainer>
  );
}





// const SignUp = ({ navigation, route }) => {
//   return <Text>This is {route.params.name}'s profile</Text>;
// };
