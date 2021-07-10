import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
  Button
} from 'react-native';
 
// We are importing the native Java module here
import {NativeModules} from 'react-native';
var StartBroker = NativeModules.StartBroker;
 
type Props = {};
export default class App extends Component<Props> {
 
  // async function to call the Java native method
  async startBroker() {
    StartBroker.startBroker( (err) => {console.log(err)}, (msg) => {console.log(msg)} );
  }

   async stopBroker() {
    StartBroker.stopBroker( (err) => {console.log(err)}, (msg) => {console.log(msg)} );
  }
 
  render() {
    return (
      <View style={styles.container}>  
              <Button
                onPress={this.startBroker}
                title="Start Broker"
                color="#841584"
              />
              <Button
                onPress={this.stopBroker}
                title="Stop Broker"
                color="#81784"
                
              />
      </View>
      
    );
  }
}
 
const styles = StyleSheet.create({
  container: {
    marginVertical:10,
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  }
});