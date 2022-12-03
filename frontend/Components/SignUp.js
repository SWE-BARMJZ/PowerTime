import { Button,Text,View } from 'react-native';

export const SignUp = ({ navigation}) => {
    return (
        <View>
            <Button
            title="Confirm Sign Up"
            onPress={() =>
                navigation.navigate('Home')
            }
            />
            <Button
            title="Back to Login"
            onPress={() =>
                navigation.navigate('Login')
            }
            />
        </View>
    );  };

