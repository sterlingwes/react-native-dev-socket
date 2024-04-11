import {NativeEventEmitter, NativeModules} from 'react-native'

const listeners: Array<[string, (message: string) => any]> = []

if (__DEV__) {
  const eventEmitter = new NativeEventEmitter(NativeModules.RTNDevSocket)
  eventEmitter.addListener('broadcast', (eventReceived: string) => {
    const [userEventName, ...payloadParts] = eventReceived.split(':')
    const payload = JSON.parse(payloadParts.join(':'))
    listeners
      .filter(([eventName]) => eventName === userEventName)
      .forEach(([, listener]) => listener(payload))
  })
}

const addEventListener = (eventName: string, handler: () => any) => {
  listeners.push([eventName, handler])
}

export const RNDevSocket = {addEventListener}
