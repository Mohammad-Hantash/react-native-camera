#import <UIKit/UIKit.h>
#import <AVFoundation/AVFoundation.h>
#import "CameraFocusSquare.h"

@class RCTCameraManager;

@interface RCTCamera : UIView

- (id)initWithManager:(RCTCameraManager*)manager bridge:(RCTBridge *)bridge;
<<<<<<< HEAD:ios/RCTCamera.h
@property BOOL isViewAdded;
@property CALayer * roundedView;
-(void) updatePreviewColor:(int )red green:(int)green blue:(int)blue;
=======

@property (nonatomic, strong) RCTCameraFocusSquare *camFocus;
>>>>>>> 57bc327d847ccb7ea90ded51a5a82168388b1349:ios/RCT/RCTCamera.h
@end
