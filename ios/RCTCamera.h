#import <UIKit/UIKit.h>
#import <AVFoundation/AVFoundation.h>
#import "CameraFocusSquare.h"

@class RCTCameraManager;

@interface RCTCamera : UIView

- (id)initWithManager:(RCTCameraManager*)manager bridge:(RCTBridge *)bridge;
@property BOOL isViewAdded;
@property CALayer * roundedView;
-(void) updatePreviewColor:(int )red green:(int)green blue:(int)blue;
@property BOOL enableColorExtraction;
@end
