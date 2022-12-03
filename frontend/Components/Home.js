import { Button,Text,View } from 'react-native';

export const Home = ({ navigation }) => {
    return (
        <View>
            <Text>Welcome Home 😁</Text>
            <Button
            title="Log out"
            onPress={() =>
                navigation.navigate('Login')
            }
            />
        </View>
    );
  };
