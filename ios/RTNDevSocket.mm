#import "RTNDevSocket.h"

@implementation RTNDevSocket

RCT_EXPORT_MODULE()

- (instancetype)init {
    self = [super init];
    #if DEBUG
    [[RCTPackagerConnection sharedPackagerConnection] addNotificationHandler:^(id userEvent) {
        [self sendEventWithName:@"broadcast" result:userEvent];
    } queue:dispatch_get_main_queue() forMethod:@"devSocket"];
    #endif
    return self;
}

+ (BOOL)requiresMainQueueSetup {
    return YES;
}

- (NSArray<NSString*> *)supportedEvents {
    return @[@"broadcast"];
}

- (void)sendEventWithName:(NSString * _Nonnull)name result:(NSString *)userEvent {
    [self sendEventWithName:name body:userEvent];
}

@end
