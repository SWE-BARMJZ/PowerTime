import React from 'react';
import {Text,View,Platform,Image,StyleSheet} from 'react-native';

export const Imager = ({ navigation }) => {
    return (
        <View style={{flex:1}}>
        {
            Platform.OS === 'web' && <>
            <Image
                source={require('../assets/images/themeImage.png')}
                style={Styles.image}
            >
            </Image>
            </>
        }
        </View>
    );
}

const Styles = StyleSheet.create({
    image: {
        width: '100%',
        height: '100%',
        resizeMode: 'cover'
    }
});
