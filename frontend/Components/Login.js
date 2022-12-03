import { Button,Text,View } from 'react-native';

export const Login = ({ navigation}) => {
    return (
        <View>
            <Button
            title="Confirm Log in"
            onPress={() =>
                navigation.navigate('Home')
            }
            />
            <Button
            title="Sign up"
            onPress={() =>
                navigation.navigate('SignUp')
            }
            />
        </View>
    );
};
